package se.birchware.chucken.block;

import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumWorldBlockLayer;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import se.birchware.chucken.util.Ref;

public class BlockChuck extends BlockBase implements ITileEntityProvider
{
/*
  private TileChuck tile_chuck;
  public EntityPlayer player;
*/

  public BlockChuck() {
    super(Material.rock);
    this.setUnlocalizedName(Ref.BLOCK_CHUCK);
    this.setCreativeTab(CreativeTabs.tabTransport);
  }

  // the block will render in the SOLID layer.  See http://greyminecraftcoder.blogspot.co.at/2014/12/block-rendering-18.html for more information.
  @SideOnly(Side.CLIENT)
  public EnumWorldBlockLayer getBlockLayer() {
    return EnumWorldBlockLayer.SOLID;
  }

  // used by the renderer to control lighting and visibility of other blocks.
  // set to true because this block is opaque and occupies the entire 1x1x1 space
  // not strictly required because the default (super method) is true
  @Override
  public boolean isOpaqueCube() {
    return true;
  }

  // used by the renderer to control lighting and visibility of other blocks, also by
  // (eg) wall or fence to control whether the fence joins itself to this block
  // set to true because this block occupies the entire 1x1x1 space
  // not strictly required because the default (super method) is true
  @Override
  public boolean isFullCube() {
    return true;
  }

  // render using a BakedModel (mbe01_block_simple.json --> mbe01_block_simple_model.json)
  // not strictly required because the default (super method) is 3.
  @Override
  public int getRenderType() {
    return 3;
  }

  @Override
  public TileEntity createNewTileEntity(World worldIn,int meta) {
  /*
    this.tile_chuck = new TileChuck();
    return this.tile_chuck;
  */
    return new TileChuck();
  }

/*
  @Override
  public boolean onBlockActivated(World worldIn,BlockPos pos,IBlockState state,EntityPlayer playerIn,EnumFacing side,float hitX,float hitY,float hitZ) {
    Logg.info("***BlockChuck() onBlockActivated " + playerIn.getDisplayName().getUnformattedText());
    this.player = playerIn;
    tile_chuck.setPlayer(playerIn);
    return super.onBlockActivated(worldIn,pos,state,playerIn,side,hitX,hitY,hitZ);
  }
*/

/*
  @Override
  public IBlockState onBlockPlaced(World worldIn, BlockPos pos, EnumFacing facing, float hitX, float hitY, float hitZ, int meta, EntityLivingBase placer) {
    EntityPlayer p = (EntityPlayer)placer;
    tile_chuck.setPlayer(p);
    Logg.info("***BlockChuck() onBlockPlaced " + p.getDisplayName().getUnformattedText());
    return this.getStateFromMeta(meta);
  }
*/

/*
  @Override
  public void onBlockPlacedBy(World world,BlockPos pos,IBlockState state,EntityLivingBase entity,ItemStack stack) {
    super.onBlockPlacedBy(world, pos, state, entity, stack);
    TileEntity tile = world.getTileEntity(pos);
    if (tile instanceof TileChuck) {
      ((TileChuck)tile).onBlockPlacedBy(entity,stack);
    }
  }
*/

}
