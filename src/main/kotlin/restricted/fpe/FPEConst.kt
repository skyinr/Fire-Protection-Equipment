package restricted.fpe

import net.minecraft.core.Direction
import net.minecraft.core.Holder
import net.minecraft.resources.ResourceLocation
import net.minecraft.sounds.SoundEvent
import net.minecraft.sounds.SoundEvents
import net.minecraft.util.Mth
import net.minecraft.world.damagesource.DamageSource
import net.minecraft.world.entity.EquipmentSlot
import net.minecraft.world.item.*
import net.minecraft.world.item.crafting.Ingredient
import net.minecraft.world.item.enchantment.EnchantmentCategory
import net.minecraft.world.level.block.SoundType
import net.minecraft.world.level.block.state.BlockBehaviour
import net.minecraft.world.level.block.state.properties.EnumProperty
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature
import net.minecraft.world.level.levelgen.feature.configurations.RandomPatchConfiguration
import net.minecraft.world.level.levelgen.placement.PlacedFeature
import net.minecraft.world.level.material.Material

private typealias ItemProperties = Item.Properties
private typealias BlockProperties = BlockBehaviour.Properties

object FPEConst {

	object BlockConst {

		// BlockBehavior.Properties

		val FireHydrantProp: BlockProperties =
			BlockProperties.of(Material.HEAVY_METAL).strength(5.0F, 1200.0F).sound(SoundType.ANVIL).noOcclusion()

		val FireExtinguishingBombProp: BlockProperties =
			BlockProperties.of(Material.EXPLOSIVE).instabreak().sound(SoundType.GRASS)

		val FireDetectorProp: BlockProperties =
			BlockProperties.of(Material.WOOL).strength(0.8F).sound(SoundType.WOOL).noOcclusion().lightLevel { 3 }

		val FireSprinklerProp: BlockProperties =
			BlockProperties.of(Material.WOOL).strength(0.8F).sound(SoundType.WOOL).noOcclusion().lightLevel { 3 }

		val HomeFireStationProp: BlockProperties =
			BlockProperties.of(Material.HEAVY_METAL).strength(5.0F, 1200.0F).sound(SoundType.ANVIL).noOcclusion()

		// Property for BlockState

		val VERTICAL_FACING: EnumProperty<Direction> =
			EnumProperty.create("facing", Direction::class.java, Direction.UP, Direction.DOWN)

	}

	object ItemConst {

		// Item.Properties

		val DefaultItemProp: ItemProperties =
			Item.Properties().tab(FPE.Tabs.Default)

		val DefaultNonStackableItemProp: ItemProperties =
			Item.Properties().stacksTo(1).tab(FPE.Tabs.Default)

		val FireExtinguisherProp: ItemProperties =
			Item.Properties().rarity(Rarity.UNCOMMON).tab(FPE.Tabs.Default).stacksTo(1).durability(15000)
	}

	object DamageSourceConst {

		val SpreadingFire = DamageSource("spreading_fire")

		val Extinguish = DamageSource("extinguish")
	}

	object EnchantCategory {
		val BowAndCrossbowCategory: EnchantmentCategory = EnchantmentCategory.create("bow_and_crossbow") { it is BowItem || it is CrossbowItem }
	}

	object CriteriaNames {

		val BlockExtinguishedTriggerID = ResourceLocation(ModId, "block_extinguished")
		val PlayerIgnitedTriggerID = ResourceLocation(ModId, "player_ignited")
	}

	val FirefighterSuitMaterials = object : ArmorMaterial {
		override fun getDurabilityForSlot(pSlot: EquipmentSlot): Int {
			return Mth.floor(ArmorMaterials.IRON.getDurabilityForSlot(pSlot) * 1.5)
		}

		override fun getDefenseForSlot(pSlot: EquipmentSlot): Int {
			return ArmorMaterials.IRON.getDefenseForSlot(pSlot)
		}

		override fun getEnchantmentValue(): Int {
			return ArmorMaterials.GOLD.enchantmentValue
		}

		override fun getEquipSound(): SoundEvent {
			return SoundEvents.WOOL_PLACE
		}

		override fun getRepairIngredient(): Ingredient {
			return Ingredient.of(MinecraftItems.WHITE_WOOL)
		}

		override fun getName(): String {
			return "$ModId:firefighter"
		}

		override fun getToughness(): Float {
			return ArmorMaterials.DIAMOND.toughness
		}

		override fun getKnockbackResistance(): Float {
			return ArmorMaterials.IRON.knockbackResistance
		}
	}

	object Features {
		lateinit var NaturalFireHydrant: Holder<ConfiguredFeature<RandomPatchConfiguration, *>>
	}

	object Placements {
		lateinit var NaturalFireHydrant: Holder<PlacedFeature>
	}

}