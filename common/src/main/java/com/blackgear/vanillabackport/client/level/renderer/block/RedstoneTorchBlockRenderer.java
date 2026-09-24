package com.blackgear.vanillabackport.client.level.renderer.block;

import com.blackgear.platform.client.v2.render.BlockRendererRegistry;
import com.blackgear.platform.core.util.event.ResultHolder;
import com.blackgear.vanillabackport.core.VanillaBackport;
import net.minecraft.client.renderer.block.BlockModelShaper;
import net.minecraft.client.resources.model.BakedModel;
import net.minecraft.client.resources.model.ModelResourceLocation;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.RedstoneTorchBlock;
import net.minecraft.world.level.block.state.BlockState;

import java.util.Map;

public class RedstoneTorchBlockRenderer implements BlockRendererRegistry.Renderer {
  public static final RedstoneTorchBlockRenderer INSTANCE = new RedstoneTorchBlockRenderer();

  private static final ModelResourceLocation MODEL_ON = new ModelResourceLocation(VanillaBackport.resource(BuiltInRegistries.BLOCK.getKey(Blocks.REDSTONE_TORCH).getPath()), "lit=true");
  private static final ModelResourceLocation MODEL_OFF = new ModelResourceLocation(VanillaBackport.resource(BuiltInRegistries.BLOCK.getKey(Blocks.REDSTONE_TORCH).getPath()), "lit=false");

  @Override
  public ResultHolder<BakedModel> render(BlockState state, BlockModelShaper blockModelShaper) {
    ModelResourceLocation location = state.getValue(RedstoneTorchBlock.LIT) ? MODEL_ON : MODEL_OFF;
    return ResultHolder.submit(blockModelShaper.getModelManager().getModel(location));
  }

  @Override
  public Map<ModelResourceLocation, ResourceLocation> registerModels() {
    return Map.of(
        MODEL_ON, VanillaBackport.resource("block/redstone_torch"),
        MODEL_OFF, VanillaBackport.resource("block/redstone_torch_off")
    );
  }

  @Override
  public boolean shouldUse() {
    return VanillaBackport.CLIENT_CONFIG.hasFallingLeaves.get();
  }
}
