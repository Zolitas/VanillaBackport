package com.blackgear.vanillabackport.client.level.renderer.item;

import com.blackgear.platform.client.v2.render.ItemRendererRegistry;
import com.blackgear.platform.core.util.event.ResultHolder;
import com.blackgear.vanillabackport.core.VanillaBackport;
import net.minecraft.client.renderer.ItemModelShaper;
import net.minecraft.client.resources.model.BakedModel;
import net.minecraft.client.resources.model.ModelResourceLocation;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.item.ItemDisplayContext;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.ItemLike;

import java.util.Set;

public class RedstoneTorchItemRenderer implements ItemRendererRegistry.Renderer {
  public static final RedstoneTorchItemRenderer INSTANCE = new RedstoneTorchItemRenderer();
  public static final Set<ItemLike> ITEMS = Set.of(Items.REDSTONE_TORCH);

  private static final ModelResourceLocation MODEL = new ModelResourceLocation(VanillaBackport.resource(BuiltInRegistries.ITEM.getKey(Items.REDSTONE_TORCH).getPath()), "inventory");

  @Override
  public ResultHolder<BakedModel> renderFirstPerson(ItemStack itemStack, ItemDisplayContext itemDisplayContext, ItemModelShaper shaper) {
    return ResultHolder.submit(shaper.getModelManager().getModel(MODEL));
  }

  @Override
  public ResultHolder<BakedModel> renderThirdPerson(ItemStack itemStack, ItemModelShaper shaper) {
    return ResultHolder.submit(shaper.getModelManager().getModel(MODEL));
  }

  @Override
  public Set<ModelResourceLocation> registerModels() {
    return Set.of(MODEL);
  }

  @Override
  public boolean shouldUse() {
    return VanillaBackport.CLIENT_CONFIG.hasFallingLeaves.get();
  }
}
