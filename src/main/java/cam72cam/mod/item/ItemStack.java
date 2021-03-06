package cam72cam.mod.item;

import cam72cam.mod.entity.Player;
import cam72cam.mod.serialization.TagCompound;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.tileentity.TileEntityFurnace;
import net.minecraftforge.fluids.FluidUtil;

public class ItemStack {
    public static final ItemStack EMPTY = new ItemStack(net.minecraft.item.ItemStack.EMPTY);

    public final net.minecraft.item.ItemStack internal;
    private final Item item;

    public ItemStack(net.minecraft.item.ItemStack internal) {
        this.internal = internal;
        this.item = internal.getItem();
    }

    public ItemStack(Item item, int count) {
        this(new net.minecraft.item.ItemStack(item, count));
    }

    public ItemStack(TagCompound bedItem) {
        this(new net.minecraft.item.ItemStack(bedItem.internal));
    }

    public ItemStack(Item item, int count, int meta) {
        this(new net.minecraft.item.ItemStack(item, count, meta));
    }

    public ItemStack(Block block) {
        this(new net.minecraft.item.ItemStack(block));
    }

    public ItemStack(Block block, int count, int meta) {
        this(new net.minecraft.item.ItemStack(block, count, meta));
    }

    public ItemStack(Item item) {
        this(new net.minecraft.item.ItemStack(item));
    }

    public ItemStack(ItemBase item, int count) {
        this(item.internal, count);
    }

    public ItemStack(String item, int i, int meta) {
        this(Item.getByNameOrId(item), i, meta);
    }

    public TagCompound getTagCompound() {
        if (internal.getTagCompound() == null) {
            internal.setTagCompound(new TagCompound().internal);
        }
        return new TagCompound(internal.getTagCompound());
    }

    public void setTagCompound(TagCompound data) {
        internal.setTagCompound(data.internal);
    }

    public ItemStack copy() {
        return new ItemStack(internal.copy());
    }

    public TagCompound toTag() {
        return new TagCompound(internal.serializeNBT());
    }

    public int getCount() {
        return internal.getCount();
    }

    public void setCount(int count) {
        internal.setCount(count);
    }

    public String getDisplayName() {
        return internal.getDisplayName();
    }

    public boolean isEmpty() {
        return internal.isEmpty();
    }

    public void shrink(int i) {
        internal.shrink(i);
    }

    public boolean equals(ItemStack other) {
        return internal.isItemEqual(other.internal);
    }

    public boolean is(Fuzzy fuzzy) {
        return fuzzy.matches(this);
    }

    public boolean is(ItemBase item) {
        return item.internal == this.item;
    }

    public boolean isFluidContainer() {
        return FluidUtil.getFluidHandler(internal) != null;
    }

    public boolean isFlammable() {
        return getBurnTime() != 0;
    }

    public int getBurnTime() {
        return TileEntityFurnace.getItemBurnTime(internal);
    }

    public int getLimit() {
        return internal.getMaxStackSize();
    }

    public boolean isValidTool(ToolType tool) {
        return item.getToolClasses(internal).contains(tool.toString());
    }

    @Override
    public String toString() {
        return internal.toString();
    }

    public void damageItem(int i, Player player) {
        internal.damageItem(i, player.internal);
    }

    public void clearTagCompound() {
        internal.setTagCompound(null);
    }
}
