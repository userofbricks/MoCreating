package net.minecraft.client.gui.screen;

import com.google.common.collect.Lists;
import com.mojang.blaze3d.platform.GlStateManager;
import java.util.List;
import java.util.ListIterator;
import net.minecraft.client.gui.AbstractGui;
import net.minecraft.client.gui.IGuiEventListener;
import net.minecraft.client.gui.chat.NarratorChatListener;
import net.minecraft.client.gui.widget.button.Button;
import net.minecraft.client.gui.widget.button.ChangePageButton;
import net.minecraft.client.renderer.BufferBuilder;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.nbt.ListNBT;
import net.minecraft.nbt.StringNBT;
import net.minecraft.network.play.client.CEditBookPacket;
import net.minecraft.util.Hand;
import net.minecraft.util.SharedConstants;
import net.minecraft.util.Util;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.text.TextFormatting;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class EditBookScreen extends Screen {
   private final PlayerEntity editingPlayer;
   private final ItemStack book;
   /** Whether the book's title or contents has been modified since being opened */
   private boolean bookIsModified;
   /** Whether the book is signed or can still be edited */
   private boolean bookIsUnsigned;
   /** Update ticks since the gui was opened */
   private int updateCount;
   private int currPage;
   private final List<String> bookPages = Lists.newArrayList();
   private String bookTitle = "";
   private int field_214240_i;
   private int field_214241_j;
   private long field_214242_k;
   private int cachedPage = -1;
   private ChangePageButton buttonNextPage;
   private ChangePageButton buttonPreviousPage;
   private Button buttonDone;
   private Button buttonSign;
   private Button buttonFinalize;
   private Button buttonCancel;
   private final Hand hand;

   public EditBookScreen(PlayerEntity p_i51100_1_, ItemStack p_i51100_2_, Hand p_i51100_3_) {
      super(NarratorChatListener.EMPTY);
      this.editingPlayer = p_i51100_1_;
      this.book = p_i51100_2_;
      this.hand = p_i51100_3_;
      CompoundNBT compoundnbt = p_i51100_2_.getTag();
      if (compoundnbt != null) {
         ListNBT listnbt = compoundnbt.getList("pages", 8).copy();

         for(int i = 0; i < listnbt.size(); ++i) {
            this.bookPages.add(listnbt.getString(i));
         }
      }

      if (this.bookPages.isEmpty()) {
         this.bookPages.add("");
      }

   }

   private int func_214199_a() {
      return this.bookPages.size();
   }

   public void tick() {
      super.tick();
      ++this.updateCount;
   }

   protected void init() {
      this.minecraft.keyboardListener.enableRepeatEvents(true);
      this.buttonSign = this.addButton(new Button(this.width / 2 - 100, 196, 98, 20, I18n.format("book.signButton"), (p_214201_1_) -> {
         this.bookIsUnsigned = true;
         this.updateButtons();
      }));
      this.buttonDone = this.addButton(new Button(this.width / 2 + 2, 196, 98, 20, I18n.format("gui.done"), (p_214204_1_) -> {
         this.minecraft.displayGuiScreen((Screen)null);
         this.sendBookToServer(false);
      }));
      this.buttonFinalize = this.addButton(new Button(this.width / 2 - 100, 196, 98, 20, I18n.format("book.finalizeButton"), (p_214195_1_) -> {
         if (this.bookIsUnsigned) {
            this.sendBookToServer(true);
            this.minecraft.displayGuiScreen((Screen)null);
         }

      }));
      this.buttonCancel = this.addButton(new Button(this.width / 2 + 2, 196, 98, 20, I18n.format("gui.cancel"), (p_214212_1_) -> {
         if (this.bookIsUnsigned) {
            this.bookIsUnsigned = false;
         }

         this.updateButtons();
      }));
      int i = (this.width - 192) / 2;
      int j = 2;
      this.buttonNextPage = this.addButton(new ChangePageButton(i + 116, 159, true, (p_214208_1_) -> {
         this.nextPage();
      }, true));
      this.buttonPreviousPage = this.addButton(new ChangePageButton(i + 43, 159, false, (p_214205_1_) -> {
         this.previousPage();
      }, true));
      this.updateButtons();
   }

   private String func_214219_a(String p_214219_1_) {
      StringBuilder stringbuilder = new StringBuilder();

      for(char c0 : p_214219_1_.toCharArray()) {
         if (c0 != 167 && c0 != 127) {
            stringbuilder.append(c0);
         }
      }

      return stringbuilder.toString();
   }

   /**
    * Displays the previous page
    */
   private void previousPage() {
      if (this.currPage > 0) {
         --this.currPage;
         this.field_214240_i = 0;
         this.field_214241_j = this.field_214240_i;
      }

      this.updateButtons();
   }

   /**
    * Displays the next page (creating it if necessary)
    */
   private void nextPage() {
      if (this.currPage < this.func_214199_a() - 1) {
         ++this.currPage;
         this.field_214240_i = 0;
         this.field_214241_j = this.field_214240_i;
      } else {
         this.func_214215_f();
         if (this.currPage < this.func_214199_a() - 1) {
            ++this.currPage;
         }

         this.field_214240_i = 0;
         this.field_214241_j = this.field_214240_i;
      }

      this.updateButtons();
   }

   public void removed() {
      this.minecraft.keyboardListener.enableRepeatEvents(false);
   }

   /**
    * Sets visibility for book buttons
    */
   private void updateButtons() {
      this.buttonPreviousPage.visible = !this.bookIsUnsigned && this.currPage > 0;
      this.buttonNextPage.visible = !this.bookIsUnsigned;
      this.buttonDone.visible = !this.bookIsUnsigned;
      this.buttonSign.visible = !this.bookIsUnsigned;
      this.buttonCancel.visible = this.bookIsUnsigned;
      this.buttonFinalize.visible = this.bookIsUnsigned;
      this.buttonFinalize.active = !this.bookTitle.trim().isEmpty();
   }

   private void func_214213_e() {
      ListIterator<String> listiterator = this.bookPages.listIterator(this.bookPages.size());

      while(listiterator.hasPrevious() && listiterator.previous().isEmpty()) {
         listiterator.remove();
      }

   }

   private void sendBookToServer(boolean p_214198_1_) {
      if (this.bookIsModified) {
         this.func_214213_e();
         ListNBT listnbt = new ListNBT();
         this.bookPages.stream().map(StringNBT::new).forEach(listnbt::add);
         if (!this.bookPages.isEmpty()) {
            this.book.setTagInfo("pages", listnbt);
         }

         if (p_214198_1_) {
            this.book.setTagInfo("author", new StringNBT(this.editingPlayer.getGameProfile().getName()));
            this.book.setTagInfo("title", new StringNBT(this.bookTitle.trim()));
         }

         this.minecraft.getConnection().sendPacket(new CEditBookPacket(this.book, p_214198_1_, this.hand));
      }
   }

   private void func_214215_f() {
      if (this.func_214199_a() < 100) {
         this.bookPages.add("");
         this.bookIsModified = true;
      }
   }

   public boolean keyPressed(int p_keyPressed_1_, int p_keyPressed_2_, int p_keyPressed_3_) {
      if (super.keyPressed(p_keyPressed_1_, p_keyPressed_2_, p_keyPressed_3_)) {
         return true;
      } else {
         return this.bookIsUnsigned ? this.func_214196_c(p_keyPressed_1_, p_keyPressed_2_, p_keyPressed_3_) : this.func_214230_b(p_keyPressed_1_, p_keyPressed_2_, p_keyPressed_3_);
      }
   }

   public boolean charTyped(char p_charTyped_1_, int p_charTyped_2_) {
      if (super.charTyped(p_charTyped_1_, p_charTyped_2_)) {
         return true;
      } else if (this.bookIsUnsigned) {
         if (this.bookTitle.length() < 16 && SharedConstants.isAllowedCharacter(p_charTyped_1_)) {
            this.bookTitle = this.bookTitle + Character.toString(p_charTyped_1_);
            this.updateButtons();
            this.bookIsModified = true;
            return true;
         } else {
            return false;
         }
      } else if (SharedConstants.isAllowedCharacter(p_charTyped_1_)) {
         this.func_214202_k(Character.toString(p_charTyped_1_));
         return true;
      } else {
         return false;
      }
   }

   private boolean func_214230_b(int p_214230_1_, int p_214230_2_, int p_214230_3_) {
      String s = this.func_214193_h();
      if (Screen.isSelectAll(p_214230_1_)) {
         this.field_214241_j = 0;
         this.field_214240_i = s.length();
         return true;
      } else if (Screen.isCopy(p_214230_1_)) {
         this.minecraft.keyboardListener.setClipboardString(this.func_214231_i());
         return true;
      } else if (Screen.isPaste(p_214230_1_)) {
         this.func_214202_k(this.func_214219_a(TextFormatting.getTextWithoutFormattingCodes(this.minecraft.keyboardListener.getClipboardString().replaceAll("\\r", ""))));
         this.field_214241_j = this.field_214240_i;
         return true;
      } else if (Screen.isCut(p_214230_1_)) {
         this.minecraft.keyboardListener.setClipboardString(this.func_214231_i());
         this.func_214192_g();
         return true;
      } else {
         switch(p_214230_1_) {
         case 257:
         case 335:
            this.func_214202_k("\n");
            return true;
         case 259:
            this.func_214207_b(s);
            return true;
         case 261:
            this.func_214221_c(s);
            return true;
         case 262:
            this.func_214218_e(s);
            return true;
         case 263:
            this.func_214200_d(s);
            return true;
         case 264:
            this.func_214209_g(s);
            return true;
         case 265:
            this.func_214197_f(s);
            return true;
         case 266:
            this.buttonPreviousPage.onPress();
            return true;
         case 267:
            this.buttonNextPage.onPress();
            return true;
         case 268:
            this.func_214220_h(s);
            return true;
         case 269:
            this.func_214211_i(s);
            return true;
         default:
            return false;
         }
      }
   }

   private void func_214207_b(String p_214207_1_) {
      if (!p_214207_1_.isEmpty()) {
         if (this.field_214241_j != this.field_214240_i) {
            this.func_214192_g();
         } else if (this.field_214240_i > 0) {
            String s = (new StringBuilder(p_214207_1_)).deleteCharAt(Math.max(0, this.field_214240_i - 1)).toString();
            this.func_214217_j(s);
            this.field_214240_i = Math.max(0, this.field_214240_i - 1);
            this.field_214241_j = this.field_214240_i;
         }
      }

   }

   private void func_214221_c(String p_214221_1_) {
      if (!p_214221_1_.isEmpty()) {
         if (this.field_214241_j != this.field_214240_i) {
            this.func_214192_g();
         } else if (this.field_214240_i < p_214221_1_.length()) {
            String s = (new StringBuilder(p_214221_1_)).deleteCharAt(Math.max(0, this.field_214240_i)).toString();
            this.func_214217_j(s);
         }
      }

   }

   private void func_214200_d(String p_214200_1_) {
      int i = this.font.getBidiFlag() ? 1 : -1;
      if (Screen.hasControlDown()) {
         this.field_214240_i = this.font.func_216863_a(p_214200_1_, i, this.field_214240_i, true);
      } else {
         this.field_214240_i = Math.max(0, this.field_214240_i + i);
      }

      if (!Screen.hasShiftDown()) {
         this.field_214241_j = this.field_214240_i;
      }

   }

   private void func_214218_e(String p_214218_1_) {
      int i = this.font.getBidiFlag() ? -1 : 1;
      if (Screen.hasControlDown()) {
         this.field_214240_i = this.font.func_216863_a(p_214218_1_, i, this.field_214240_i, true);
      } else {
         this.field_214240_i = Math.min(p_214218_1_.length(), this.field_214240_i + i);
      }

      if (!Screen.hasShiftDown()) {
         this.field_214241_j = this.field_214240_i;
      }

   }

   private void func_214197_f(String p_214197_1_) {
      if (!p_214197_1_.isEmpty()) {
         EditBookScreen.Point editbookscreen$point = this.func_214194_c(p_214197_1_, this.field_214240_i);
         if (editbookscreen$point.field_216929_c == 0) {
            this.field_214240_i = 0;
            if (!Screen.hasShiftDown()) {
               this.field_214241_j = this.field_214240_i;
            }
         } else {
            int i = this.func_214203_a(p_214197_1_, new EditBookScreen.Point(editbookscreen$point.field_216928_b + this.func_214206_a(p_214197_1_, this.field_214240_i) / 3, editbookscreen$point.field_216929_c - 9));
            if (i >= 0) {
               this.field_214240_i = i;
               if (!Screen.hasShiftDown()) {
                  this.field_214241_j = this.field_214240_i;
               }
            }
         }
      }

   }

   private void func_214209_g(String p_214209_1_) {
      if (!p_214209_1_.isEmpty()) {
         EditBookScreen.Point editbookscreen$point = this.func_214194_c(p_214209_1_, this.field_214240_i);
         int i = this.font.getWordWrappedHeight(p_214209_1_ + "" + TextFormatting.BLACK + "_", 114);
         if (editbookscreen$point.field_216929_c + 9 == i) {
            this.field_214240_i = p_214209_1_.length();
            if (!Screen.hasShiftDown()) {
               this.field_214241_j = this.field_214240_i;
            }
         } else {
            int j = this.func_214203_a(p_214209_1_, new EditBookScreen.Point(editbookscreen$point.field_216928_b + this.func_214206_a(p_214209_1_, this.field_214240_i) / 3, editbookscreen$point.field_216929_c + 9));
            if (j >= 0) {
               this.field_214240_i = j;
               if (!Screen.hasShiftDown()) {
                  this.field_214241_j = this.field_214240_i;
               }
            }
         }
      }

   }

   private void func_214220_h(String p_214220_1_) {
      this.field_214240_i = this.func_214203_a(p_214220_1_, new EditBookScreen.Point(0, this.func_214194_c(p_214220_1_, this.field_214240_i).field_216929_c));
      if (!Screen.hasShiftDown()) {
         this.field_214241_j = this.field_214240_i;
      }

   }

   private void func_214211_i(String p_214211_1_) {
      this.field_214240_i = this.func_214203_a(p_214211_1_, new EditBookScreen.Point(113, this.func_214194_c(p_214211_1_, this.field_214240_i).field_216929_c));
      if (!Screen.hasShiftDown()) {
         this.field_214241_j = this.field_214240_i;
      }

   }

   private void func_214192_g() {
      if (this.field_214241_j != this.field_214240_i) {
         String s = this.func_214193_h();
         int i = Math.min(this.field_214240_i, this.field_214241_j);
         int j = Math.max(this.field_214240_i, this.field_214241_j);
         String s1 = s.substring(0, i) + s.substring(j);
         this.field_214240_i = i;
         this.field_214241_j = this.field_214240_i;
         this.func_214217_j(s1);
      }
   }

   private int func_214206_a(String p_214206_1_, int p_214206_2_) {
      return (int)this.font.getCharWidth(p_214206_1_.charAt(MathHelper.clamp(p_214206_2_, 0, p_214206_1_.length() - 1)));
   }

   private boolean func_214196_c(int p_214196_1_, int p_214196_2_, int p_214196_3_) {
      switch(p_214196_1_) {
      case 257:
      case 335:
         if (!this.bookTitle.isEmpty()) {
            this.sendBookToServer(true);
            this.minecraft.displayGuiScreen((Screen)null);
         }

         return true;
      case 259:
         if (!this.bookTitle.isEmpty()) {
            this.bookTitle = this.bookTitle.substring(0, this.bookTitle.length() - 1);
            this.updateButtons();
         }

         return true;
      default:
         return false;
      }
   }

   private String func_214193_h() {
      return this.currPage >= 0 && this.currPage < this.bookPages.size() ? this.bookPages.get(this.currPage) : "";
   }

   private void func_214217_j(String p_214217_1_) {
      if (this.currPage >= 0 && this.currPage < this.bookPages.size()) {
         this.bookPages.set(this.currPage, p_214217_1_);
         this.bookIsModified = true;
      }

   }

   private void func_214202_k(String p_214202_1_) {
      if (this.field_214241_j != this.field_214240_i) {
         this.func_214192_g();
      }

      String s = this.func_214193_h();
      this.field_214240_i = MathHelper.clamp(this.field_214240_i, 0, s.length());
      String s1 = (new StringBuilder(s)).insert(this.field_214240_i, p_214202_1_).toString();
      int i = this.font.getWordWrappedHeight(s1 + "" + TextFormatting.BLACK + "_", 114);
      if (i <= 128 && s1.length() < 1024) {
         this.func_214217_j(s1);
         this.field_214241_j = this.field_214240_i = Math.min(this.func_214193_h().length(), this.field_214240_i + p_214202_1_.length());
      }

   }

   public void render(int p_render_1_, int p_render_2_, float p_render_3_) {
      this.renderBackground();
      this.setFocused((IGuiEventListener)null);
      GlStateManager.color4f(1.0F, 1.0F, 1.0F, 1.0F);
      this.minecraft.getTextureManager().bindTexture(ReadBookScreen.field_214167_b);
      int i = (this.width - 192) / 2;
      int j = 2;
      this.blit(i, 2, 0, 0, 192, 192);
      if (this.bookIsUnsigned) {
         String s = this.bookTitle;
         if (this.updateCount / 6 % 2 == 0) {
            s = s + "" + TextFormatting.BLACK + "_";
         } else {
            s = s + "" + TextFormatting.GRAY + "_";
         }

         String s1 = I18n.format("book.editTitle");
         int k = this.func_214225_l(s1);
         this.font.drawString(s1, (float)(i + 36 + (114 - k) / 2), 34.0F, 0);
         int l = this.func_214225_l(s);
         this.font.drawString(s, (float)(i + 36 + (114 - l) / 2), 50.0F, 0);
         String s2 = I18n.format("book.byAuthor", this.editingPlayer.getName().getString());
         int i1 = this.func_214225_l(s2);
         this.font.drawString(TextFormatting.DARK_GRAY + s2, (float)(i + 36 + (114 - i1) / 2), 60.0F, 0);
         String s3 = I18n.format("book.finalizeWarning");
         this.font.drawSplitString(s3, i + 36, 82, 114, 0);
      } else {
         String s4 = I18n.format("book.pageIndicator", this.currPage + 1, this.func_214199_a());
         String s5 = this.func_214193_h();
         int j1 = this.func_214225_l(s4);
         this.font.drawString(s4, (float)(i - j1 + 192 - 44), 18.0F, 0);
         this.font.drawSplitString(s5, i + 36, 32, 114, 0);
         this.func_214222_m(s5);
         if (this.updateCount / 6 % 2 == 0) {
            EditBookScreen.Point editbookscreen$point = this.func_214194_c(s5, this.field_214240_i);
            if (this.font.getBidiFlag()) {
               this.func_214227_a(editbookscreen$point);
               editbookscreen$point.field_216928_b = editbookscreen$point.field_216928_b - 4;
            }

            this.func_214224_c(editbookscreen$point);
            if (this.field_214240_i < s5.length()) {
               AbstractGui.fill(editbookscreen$point.field_216928_b, editbookscreen$point.field_216929_c - 1, editbookscreen$point.field_216928_b + 1, editbookscreen$point.field_216929_c + 9, -16777216);
            } else {
               this.font.drawString("_", (float)editbookscreen$point.field_216928_b, (float)editbookscreen$point.field_216929_c, 0);
            }
         }
      }

      super.render(p_render_1_, p_render_2_, p_render_3_);
   }

   private int func_214225_l(String p_214225_1_) {
      return this.font.getStringWidth(this.font.getBidiFlag() ? this.font.bidiReorder(p_214225_1_) : p_214225_1_);
   }

   private int func_214216_b(String p_214216_1_, int p_214216_2_) {
      return this.font.sizeStringToWidth(p_214216_1_, p_214216_2_);
   }

   private String func_214231_i() {
      String s = this.func_214193_h();
      int i = Math.min(this.field_214240_i, this.field_214241_j);
      int j = Math.max(this.field_214240_i, this.field_214241_j);
      return s.substring(i, j);
   }

   private void func_214222_m(String p_214222_1_) {
      if (this.field_214241_j != this.field_214240_i) {
         int i = Math.min(this.field_214240_i, this.field_214241_j);
         int j = Math.max(this.field_214240_i, this.field_214241_j);
         String s = p_214222_1_.substring(i, j);
         int k = this.font.func_216863_a(p_214222_1_, 1, j, true);
         String s1 = p_214222_1_.substring(i, k);
         EditBookScreen.Point editbookscreen$point = this.func_214194_c(p_214222_1_, i);
         EditBookScreen.Point editbookscreen$point1 = new EditBookScreen.Point(editbookscreen$point.field_216928_b, editbookscreen$point.field_216929_c + 9);

         while(!s.isEmpty()) {
            int l = this.func_214216_b(s1, 114 - editbookscreen$point.field_216928_b);
            if (s.length() <= l) {
               editbookscreen$point1.field_216928_b = editbookscreen$point.field_216928_b + this.func_214225_l(s);
               this.func_214223_a(editbookscreen$point, editbookscreen$point1);
               break;
            }

            l = Math.min(l, s.length() - 1);
            String s2 = s.substring(0, l);
            char c0 = s.charAt(l);
            boolean flag = c0 == ' ' || c0 == '\n';
            s = TextFormatting.getFormatString(s2) + s.substring(l + (flag ? 1 : 0));
            s1 = TextFormatting.getFormatString(s2) + s1.substring(l + (flag ? 1 : 0));
            editbookscreen$point1.field_216928_b = editbookscreen$point.field_216928_b + this.func_214225_l(s2 + " ");
            this.func_214223_a(editbookscreen$point, editbookscreen$point1);
            editbookscreen$point.field_216928_b = 0;
            editbookscreen$point.field_216929_c = editbookscreen$point.field_216929_c + 9;
            editbookscreen$point1.field_216929_c = editbookscreen$point1.field_216929_c + 9;
         }

      }
   }

   private void func_214223_a(EditBookScreen.Point p_214223_1_, EditBookScreen.Point p_214223_2_) {
      EditBookScreen.Point editbookscreen$point = new EditBookScreen.Point(p_214223_1_.field_216928_b, p_214223_1_.field_216929_c);
      EditBookScreen.Point editbookscreen$point1 = new EditBookScreen.Point(p_214223_2_.field_216928_b, p_214223_2_.field_216929_c);
      if (this.font.getBidiFlag()) {
         this.func_214227_a(editbookscreen$point);
         this.func_214227_a(editbookscreen$point1);
         int i = editbookscreen$point1.field_216928_b;
         editbookscreen$point1.field_216928_b = editbookscreen$point.field_216928_b;
         editbookscreen$point.field_216928_b = i;
      }

      this.func_214224_c(editbookscreen$point);
      this.func_214224_c(editbookscreen$point1);
      Tessellator tessellator = Tessellator.getInstance();
      BufferBuilder bufferbuilder = tessellator.getBuffer();
      GlStateManager.color4f(0.0F, 0.0F, 255.0F, 255.0F);
      GlStateManager.disableTexture();
      GlStateManager.enableColorLogicOp();
      GlStateManager.logicOp(GlStateManager.LogicOp.OR_REVERSE);
      bufferbuilder.begin(7, DefaultVertexFormats.POSITION);
      bufferbuilder.pos((double)editbookscreen$point.field_216928_b, (double)editbookscreen$point1.field_216929_c, 0.0D).endVertex();
      bufferbuilder.pos((double)editbookscreen$point1.field_216928_b, (double)editbookscreen$point1.field_216929_c, 0.0D).endVertex();
      bufferbuilder.pos((double)editbookscreen$point1.field_216928_b, (double)editbookscreen$point.field_216929_c, 0.0D).endVertex();
      bufferbuilder.pos((double)editbookscreen$point.field_216928_b, (double)editbookscreen$point.field_216929_c, 0.0D).endVertex();
      tessellator.draw();
      GlStateManager.disableColorLogicOp();
      GlStateManager.enableTexture();
   }

   private EditBookScreen.Point func_214194_c(String p_214194_1_, int p_214194_2_) {
      EditBookScreen.Point editbookscreen$point = new EditBookScreen.Point();
      int i = 0;
      int j = 0;

      for(String s = p_214194_1_; !s.isEmpty(); j = i) {
         int k = this.func_214216_b(s, 114);
         if (s.length() <= k) {
            String s3 = s.substring(0, Math.min(Math.max(p_214194_2_ - j, 0), s.length()));
            editbookscreen$point.field_216928_b = editbookscreen$point.field_216928_b + this.func_214225_l(s3);
            break;
         }

         String s1 = s.substring(0, k);
         char c0 = s.charAt(k);
         boolean flag = c0 == ' ' || c0 == '\n';
         s = TextFormatting.getFormatString(s1) + s.substring(k + (flag ? 1 : 0));
         i += s1.length() + (flag ? 1 : 0);
         if (i - 1 >= p_214194_2_) {
            String s2 = s1.substring(0, Math.min(Math.max(p_214194_2_ - j, 0), s1.length()));
            editbookscreen$point.field_216928_b = editbookscreen$point.field_216928_b + this.func_214225_l(s2);
            break;
         }

         editbookscreen$point.field_216929_c = editbookscreen$point.field_216929_c + 9;
      }

      return editbookscreen$point;
   }

   private void func_214227_a(EditBookScreen.Point p_214227_1_) {
      if (this.font.getBidiFlag()) {
         p_214227_1_.field_216928_b = 114 - p_214227_1_.field_216928_b;
      }

   }

   private void func_214210_b(EditBookScreen.Point p_214210_1_) {
      p_214210_1_.field_216928_b = p_214210_1_.field_216928_b - (this.width - 192) / 2 - 36;
      p_214210_1_.field_216929_c = p_214210_1_.field_216929_c - 32;
   }

   private void func_214224_c(EditBookScreen.Point p_214224_1_) {
      p_214224_1_.field_216928_b = p_214224_1_.field_216928_b + (this.width - 192) / 2 + 36;
      p_214224_1_.field_216929_c = p_214224_1_.field_216929_c + 32;
   }

   private int func_214226_d(String p_214226_1_, int p_214226_2_) {
      if (p_214226_2_ < 0) {
         return 0;
      } else {
         float f1 = 0.0F;
         boolean flag = false;
         String s = p_214226_1_ + " ";

         for(int i = 0; i < s.length(); ++i) {
            char c0 = s.charAt(i);
            float f2 = this.font.getCharWidth(c0);
            if (c0 == 167 && i < s.length() - 1) {
               ++i;
               c0 = s.charAt(i);
               if (c0 != 'l' && c0 != 'L') {
                  if (c0 == 'r' || c0 == 'R') {
                     flag = false;
                  }
               } else {
                  flag = true;
               }

               f2 = 0.0F;
            }

            float f = f1;
            f1 += f2;
            if (flag && f2 > 0.0F) {
               ++f1;
            }

            if ((float)p_214226_2_ >= f && (float)p_214226_2_ < f1) {
               return i;
            }
         }

         if ((float)p_214226_2_ >= f1) {
            return s.length() - 1;
         } else {
            return -1;
         }
      }
   }

   private int func_214203_a(String p_214203_1_, EditBookScreen.Point p_214203_2_) {
      int i = 16 * 9;
      if (p_214203_2_.field_216929_c > i) {
         return -1;
      } else {
         int j = Integer.MIN_VALUE;
         int k = 9;
         int l = 0;

         for(String s = p_214203_1_; !s.isEmpty() && j < i; k += 9) {
            int i1 = this.func_214216_b(s, 114);
            if (i1 < s.length()) {
               String s1 = s.substring(0, i1);
               if (p_214203_2_.field_216929_c >= j && p_214203_2_.field_216929_c < k) {
                  int k1 = this.func_214226_d(s1, p_214203_2_.field_216928_b);
                  return k1 < 0 ? -1 : l + k1;
               }

               char c0 = s.charAt(i1);
               boolean flag = c0 == ' ' || c0 == '\n';
               s = TextFormatting.getFormatString(s1) + s.substring(i1 + (flag ? 1 : 0));
               l += s1.length() + (flag ? 1 : 0);
            } else if (p_214203_2_.field_216929_c >= j && p_214203_2_.field_216929_c < k) {
               int j1 = this.func_214226_d(s, p_214203_2_.field_216928_b);
               return j1 < 0 ? -1 : l + j1;
            }

            j = k;
         }

         return p_214203_1_.length();
      }
   }

   public boolean mouseClicked(double p_mouseClicked_1_, double p_mouseClicked_3_, int p_mouseClicked_5_) {
      if (p_mouseClicked_5_ == 0) {
         long i = Util.milliTime();
         String s = this.func_214193_h();
         if (!s.isEmpty()) {
            EditBookScreen.Point editbookscreen$point = new EditBookScreen.Point((int)p_mouseClicked_1_, (int)p_mouseClicked_3_);
            this.func_214210_b(editbookscreen$point);
            this.func_214227_a(editbookscreen$point);
            int j = this.func_214203_a(s, editbookscreen$point);
            if (j >= 0) {
               if (j == this.cachedPage && i - this.field_214242_k < 250L) {
                  if (this.field_214241_j == this.field_214240_i) {
                     this.field_214241_j = this.font.func_216863_a(s, -1, j, false);
                     this.field_214240_i = this.font.func_216863_a(s, 1, j, false);
                  } else {
                     this.field_214241_j = 0;
                     this.field_214240_i = this.func_214193_h().length();
                  }
               } else {
                  this.field_214240_i = j;
                  if (!Screen.hasShiftDown()) {
                     this.field_214241_j = this.field_214240_i;
                  }
               }
            }

            this.cachedPage = j;
         }

         this.field_214242_k = i;
      }

      return super.mouseClicked(p_mouseClicked_1_, p_mouseClicked_3_, p_mouseClicked_5_);
   }

   public boolean mouseDragged(double p_mouseDragged_1_, double p_mouseDragged_3_, int p_mouseDragged_5_, double p_mouseDragged_6_, double p_mouseDragged_8_) {
      if (p_mouseDragged_5_ == 0 && this.currPage >= 0 && this.currPage < this.bookPages.size()) {
         String s = this.bookPages.get(this.currPage);
         EditBookScreen.Point editbookscreen$point = new EditBookScreen.Point((int)p_mouseDragged_1_, (int)p_mouseDragged_3_);
         this.func_214210_b(editbookscreen$point);
         this.func_214227_a(editbookscreen$point);
         int i = this.func_214203_a(s, editbookscreen$point);
         if (i >= 0) {
            this.field_214240_i = i;
         }
      }

      return super.mouseDragged(p_mouseDragged_1_, p_mouseDragged_3_, p_mouseDragged_5_, p_mouseDragged_6_, p_mouseDragged_8_);
   }

   @OnlyIn(Dist.CLIENT)
   class Point {
      private int field_216928_b;
      private int field_216929_c;

      Point() {
      }

      Point(int p_i50636_2_, int p_i50636_3_) {
         this.field_216928_b = p_i50636_2_;
         this.field_216929_c = p_i50636_3_;
      }
   }
}