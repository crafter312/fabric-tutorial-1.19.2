package net.thesquire.tutorialmod.mixin;

import net.minecraft.entity.Entity;
import net.minecraft.nbt.NbtCompound;
import net.thesquire.tutorialmod.util.IEntityDataSaver;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

/**
 * This class is a good basic example of how to use mixin.
 * To modify an existing minecraft method, use the {@code @Inject}
 * annotation as shown. To add your own methods to an existing
 * minecraft class, implement an interface with your methods
 * and override said methods using the {@code @Override} annotation
 * like shown below. For a more complex example, see {@code ModCropBlock.java}.
 */
@Mixin(Entity.class)
public abstract class ModEntityDataSaver implements IEntityDataSaver {

    private NbtCompound persistentData;

    /************************************/

    @Override
    public NbtCompound getPersistentData() {
        if(this.persistentData == null) {
            this.persistentData = new NbtCompound();
        }
        return persistentData;
    }

    /************************************/

    @Inject(method = "writeNbt", at = @At("HEAD"))
    protected void injectWriteMethod(NbtCompound nbt, CallbackInfoReturnable info) {
        if(persistentData != null) {
            nbt.put("tutorialmod.kaupen_data", persistentData);
        }
    }

    @Inject(method = "readNbt", at = @At("HEAD"))
    protected void injectReadMethod(NbtCompound nbt, CallbackInfo info) {
        if (nbt.contains("tutorialmod.kaupen_data", 10)) {
            persistentData = nbt.getCompound("tutorialmod.kaupen_data");
        }
    }

}
