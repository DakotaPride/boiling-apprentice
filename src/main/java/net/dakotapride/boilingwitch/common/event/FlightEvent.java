package net.dakotapride.boilingwitch.common.event;

import dev.architectury.event.events.common.TickEvent;
import net.dakotapride.boilingwitch.common.register.content.EffectRegister;
import net.minecraft.client.MinecraftClient;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.world.GameMode;

public class FlightEvent implements TickEvent.Player {

    @Override
    public void tick(PlayerEntity instance) {
        execute(instance);
    }

    private static void execute(Entity entity) {
        if (entity == null)
            return;
        if (entity instanceof LivingEntity livingEntity ? livingEntity.hasStatusEffect(EffectRegister.PHANTOMESQUE) : false) {
            if (entity instanceof PlayerEntity playerEntity) {
                playerEntity.getAbilities().flying = (true);
                playerEntity.sendAbilitiesUpdate();
            }
        }
        if (!(entity instanceof LivingEntity livingEntity ? livingEntity.hasStatusEffect(EffectRegister.PHANTOMESQUE) : false)) {
            if (entity instanceof PlayerEntity playerEntity) {
                playerEntity.getAbilities().flying = (false);
                playerEntity.sendAbilitiesUpdate();
            }
        }
        if (entity.isOnGround()) {
            if (entity instanceof PlayerEntity playerEntity) {
                playerEntity.getAbilities().flying = (false);
                playerEntity.sendAbilitiesUpdate();
            }
        }
        if (new Object() {
            public boolean checkGamemode(Entity entity1) {
                if (entity1 instanceof ServerPlayerEntity serverPlayerEntity) {
                    return serverPlayerEntity.interactionManager.getGameMode() == GameMode.CREATIVE;
                } else if (entity1.world.isClient() && entity1 instanceof PlayerEntity playerEntity) {
                    return MinecraftClient.getInstance().getNetworkHandler().getPlayerListEntry(playerEntity.getGameProfile().getId()) != null && MinecraftClient.getInstance()
                            .getNetworkHandler().getPlayerListEntry(playerEntity.getGameProfile().getId()).getGameMode() == GameMode.CREATIVE;
                }
                return false;
            }
        }.checkGamemode(entity)) {
            if (entity instanceof PlayerEntity playerEntity) {
                playerEntity.getAbilities().flying = (true);
                playerEntity.sendAbilitiesUpdate();
            }
        }
        if (new Object() {
            public boolean checkGamemode(Entity entity1) {
                if (entity1 instanceof ServerPlayerEntity serverPlayerEntity) {
                    return serverPlayerEntity.interactionManager.getGameMode() == GameMode.CREATIVE;
                } else if (entity1.world.isClient() && entity1 instanceof PlayerEntity playerEntity) {
                    return MinecraftClient.getInstance().getNetworkHandler().getPlayerListEntry(playerEntity.getGameProfile().getId()) != null && MinecraftClient.getInstance()
                            .getNetworkHandler().getPlayerListEntry(playerEntity.getGameProfile().getId()).getGameMode() == GameMode.SPECTATOR;
                }
                return false;
            }
        }.checkGamemode(entity)) {
            if (entity instanceof PlayerEntity playerEntity) {
                playerEntity.getAbilities().flying = (true);
                playerEntity.sendAbilitiesUpdate();
            }
        }
    }

}
