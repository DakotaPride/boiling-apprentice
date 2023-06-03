package net.dakotapride.boilingwitch.common.register.content;

import net.dakotapride.boilingwitch.common.BoilingWitchMod;
import net.dakotapride.boilingwitch.common.entity.LightEntity;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricEntityTypeBuilder;
import net.minecraft.entity.EntityDimensions;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class EntityRegister {
    public static final EntityType<LightEntity> LIGHT = Registry.register(
            Registry.ENTITY_TYPE, new Identifier(BoilingWitchMod.MOD_ID, "light"),
            FabricEntityTypeBuilder.create(SpawnGroup.AMBIENT, LightEntity::new)
                    .dimensions(EntityDimensions.fixed(0.4f, 1.5f)).build());
}
