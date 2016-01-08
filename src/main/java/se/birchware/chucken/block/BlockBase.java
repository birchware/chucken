package se.birchware.chucken.block;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import se.birchware.chucken.util.Ref;

public class BlockBase extends Block
{
  public BlockBase(Material material) {
    super(material);
  }

/*  För public int getRenderType()

  -1 = doesn’t render at all, eg BlockAir
   1 = renders as a flowing liquid (animated texture) using BlockFluidRenderer.renderFluid – eg lava and water.
   2 = doesn’t render anything in the block layers, but has an associated TileEntitySpecialRenderer which does draw something, eg BlockChest.
   3 = renders using an IBakedModel, BlockModelRenderer.renderModel.  This is described in more detail below.
*/

  @Override
  public String getUnlocalizedName() {
    return String.format("block.%s%s",Ref.MODID + ":",getUnwrappedUnlocalizedName(super.getUnlocalizedName()));
  }

  protected String getUnwrappedUnlocalizedName(String unlocalizedName) {
    return unlocalizedName.substring(unlocalizedName.indexOf(".") + 1);
  }
}
