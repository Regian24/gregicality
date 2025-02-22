package gregicadditions;

import gregicadditions.utils.GALog;
import gregicadditions.worldgen.PumpjackHandler;
import net.minecraftforge.common.config.Config;
import net.minecraftforge.fluids.FluidRegistry;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

@Config(modid = Gregicality.MODID)
public class GAConfig {

    @Config.Comment({"Configs for Client Side"})
    public static Client client = new Client();

    public static class Client {
        @Config.Comment("Should use advanced model for casings")
        @Config.Name("Advanced Casing Model")
        @Config.RequiresMcRestart
        public boolean AdvancedCasingModel = true;
    }

    @Config.Comment("Config Options for Native EU to FE conversion")
    public static EnergyConversion EnergyConversion = new EnergyConversion();

    public static class EnergyConversion {

        @Config.Comment("Enable Native EU to RF via GT cables and wires")
        @Config.RequiresMcRestart
        public boolean enableNativeEUtoFE = true;

        @Config.Comment({"EU to FE Ratio. Can be less than 1 for multiple EU per unit of FE.",
                         "Decimal point precision will be lost if Ratio is greater than 1.",
                         "If Ratio is less than 1, precision will be lost if (1 / ratio) is not a whole number."})
        @Config.Name("EU/FE Ratio")
        @Config.RequiresMcRestart
        public double RATIO = 4;

        @Config.Comment("Energy Converter Machine Options")
        @Config.Name("Power converter sizes (in amperage)")
        @Config.RequiresMcRestart
        public int[] values = new int[]{1, 4, 9, 16};

        @Config.Name("Disable GregTech EU to FE Energy Converters?")
        @Config.RequiresMcRestart
        public boolean disableEUtoFEConverter = true;

        @Config.Name("Dusable FE to GregTech EU Energy Converters?")
        @Config.RequiresMcRestart
        public boolean disableFEtoEUConverter = false;

        @Config.Name("Should Energy Converters require matching voltage batteries?")
        public boolean PermitOnlyExactVoltage = false;
    }
    @Config.Comment("Config options for GT6 features")
    public static GT6 GT6 = new GT6();

    public static class GT6 {

        @Config.Comment("Require Bending Cylinders for some components like Rings and Pipes")
        @Config.Name("Enable Bending Cylinders")
        public boolean BendingCylinders = true;

        @Config.Comment("Add Curved Plates for Pipes, Rotors, and some late game component recipes")
        @Config.Name("Enable Curved Plates")
        public boolean addCurvedPlates = true;

        @Config.Comment("Set this to false to disable the Cluster Mill machine itself")
        @Config.Name("Enable the Cluster Mill for Foils")
        public boolean BendingFoilsAutomatic = true;

        @Config.Comment("Set this to false to enable the GT5 Wrench recipes")
        @Config.Name("Wrenches are crafted with Plates instead of Ingots")
        public boolean ExpensiveWrenches = true;

        @Config.Comment("Set this to false to disable the support for Forestry Electron Tubes")
        @Config.Name("Should Electrodes be registered?")
        public boolean electrodes = true;

        @Config.Comment("Set this to false to disable rounds")
        @Config.Name("Should rounds be registered?")
        public boolean addRounds = true;
    }

    @Config.Comment("Config options for GT5U features")
    public static GT5U GT5U = new GT5U();

    public static class GT5U {
        @Config.Comment("Change the recipe of rods to result in 1 stick and 2 small piles of dusts.")
        public boolean stickGT5U = false;

        @Config.Comment("3x3 Crafting Table Recipe Removals")
        @Config.Name("Crafting - Remove 3x3 Block Crafting Recipes from Crafting Table")
        public boolean Remove3x3BlockRecipes = false;
        @Config.Name("Crafting - Remove 3x3 Nugget Crafting Recipes from Crafting Table")
        public boolean Remove3x3NuggetRecipes = false;
        @Config.Name("Crafting - Remove 3x3 Misc Recipes from Crafting Table (all others)")
        public boolean Remove3x3MiscRecipes = false;

        @Config.Comment("1->9 Crafting Table Recipe Removals")
        @Config.Name("Crafting - Remove 1->9 Block Uncrafting Recipes from Crafting Table")
        public boolean Remove1to9BlockRecipes = false;
        @Config.Name("Crafting - Remove 1->9 Nugget Uncrafting Recipes from Crafting Table")
        public boolean Remove1to9NuggetRecipes = false;
        @Config.Name("Crafting - Remove 1-> Misc Recipes from Crafting Table (all others)")
        public boolean Remove1to9MiscRecipes = false;

        @Config.Comment("Set to false to enable Log>Charcoal smelting recipes")
        @Config.Name("All Log to Charcoal smelting recipes will be removed")
        public boolean DisableLogToCharcoalSmelting = true;

        @Config.Comment("Set these to false to disable the higher tier versions of machines")
        @Config.Name("Should higher tier Alloy Smelters be registered?")
        public boolean highTierAlloySmelter = true;
        @Config.Name("Should higher tier Arc Furnaces be registered?")
        public boolean highTierArcFurnaces = true;
        @Config.Name("Should higher tier Assembling Machines be registered?")
        public boolean highTierAssemblers = true;
        @Config.Name("Should higher tier Autoclaves be registered?")
        public boolean highTierAutoclaves = true;
        @Config.Name("Should higher tier Bending Machines be registered?")
        public boolean highTierBenders = true;
        @Config.Name("Should higher tier Breweries be registered?")
        public boolean highTierBreweries = true;
        @Config.Name("Should higher tier Canning Machines be registered?")
        public boolean highTierCanners = true;
        @Config.Name("Should higher tier Centrifuges be registered?")
        public boolean highTierCentrifuges = true;
        @Config.Name("Should higher tier Chemical Baths be registered?")
        public boolean highTierChemicalBaths = true;
        @Config.Name("Should higher tier Chemical Reactors be registered?")
        public boolean highTierChemicalReactors = true;
        @Config.Name("Should higher tier Compressors be registered?")
        public boolean highTierCompressors = true;
        @Config.Name("Should higher tier Cutting Machines be registered?")
        public boolean highTierCutters = true;
        @Config.Name("Should higher tier Cluster Mills be registered?")
        public boolean highTierClusterMills = true;
        @Config.Name("Should higher tier Distilleries be registered?")
        public boolean highTierDistilleries = true;
        @Config.Name("Should higher tier Electric Furnaces be registered?")
        public boolean highTierElectricFurnace = true;
        @Config.Name("Should higher tier Electrolyzers be registered?")
        public boolean highTierElectrolyzers = true;
        @Config.Name("Should higher tier Electromagnetic Separators be registered?")
        public boolean highTierElectromagneticSeparators = true;
        @Config.Name("Should higher tier Extractors be registered?")
        public boolean highTierExtractors = true;
        @Config.Name("Should higher tier Extruders be registered?")
        public boolean highTierExtruders = true;
        @Config.Name("Should higher tier Fermenters be registered?")
        public boolean highTierFermenters = true;
        @Config.Name("Should higher tier Fluid Canners be registered?")
        public boolean highTierFluidCanners = true;
        @Config.Name("Should higher tier Fluid Extractors be registered?")
        public boolean highTierFluidExtractors = true;
        @Config.Name("Should higher tier Fluid Heaters be registered?")
        public boolean highTierFluidHeaters = true;
        @Config.Name("Should higher tier Fluid Heaters be registered?")
        public boolean highTierFluidSolidifiers = true;
        @Config.Name("Should higher tier Forge Hammers be registered?")
        public boolean highTierForgeHammers = true;
        @Config.Name("Should higher tier Forming Presses be registered?")
        public boolean highTierFormingPresses = true;
        @Config.Name("Should higher tier Lathes be registered?")
        public boolean highTierLathes = true;
        @Config.Name("Should higher tier Microwaves be registered?")
        public boolean highTierMicrowaves = true;
        @Config.Name("Should higher tier Mixers be registered?")
        public boolean highTierMixers = true;
        @Config.Name("Should higher tier Ore Washers be registered?")
        public boolean highTierOreWashers = true;
        @Config.Name("Should higher tier Packagers be registered?")
        public boolean highTierPackers = true;
        @Config.Name("Should higher tier Plasma Arc Furnaces be registered?")
        public boolean highTierPlasmaArcFurnaces = true;
        @Config.Name("Should higher tier Polarizers be registered?")
        public boolean highTierPolarizers = true;
        @Config.Name("Should higher tier Precision Laser Engravers be registered?")
        public boolean highTierLaserEngravers = true;
        @Config.Name("Should higher tier Pumps be registered?")
        public boolean highTierPumps = true;
        @Config.Name("Should higher tier Replicators be registered?")
        public boolean highTierReplicators = true;
        @Config.Name("Should higher tier Sifting Machines be registered?")
        public boolean highTierSifters = true;
        @Config.Name("Should higher tier Thermal Centrifuges be registered?")
        public boolean highTierThermalCentrifuges = true;
        @Config.Name("Should higher tier Macerators be registered?")
        public boolean highTierMacerators = true;
        @Config.Name("Should higher tier Mass Fabricators be registered?")
        public boolean highTierMassFabs = true;
        @Config.Name("Should higher tier Unpackagers be registered?")
        public boolean highTierUnpackers = true;
        @Config.Name("Should higher tier Wiremills be registered?")
        public boolean highTierWiremills = true;
        @Config.Name("Should higher tier Chemical Dehydrators be registered?")
        public boolean highTierChemicalDehydrator = true;
        @Config.Name("Should higher tier Decay Chambers be registered?")
        public boolean highTierDecayChamber = true;
        @Config.Name("Should higher tier Green Houses be registered?")
        public boolean highTierGreenHouse = true;
        @Config.Name("Should higher tier World Accelerators be registered?")
        public boolean highTierWorldAccelerator = true;

        @Config.Comment("Set these to true to enable certain Batteries.")
        @Config.Name("Batteries - Enable an extra ZPM and UV Battery (this also makes the Ultimate Battery harder to make)")
        public boolean enableZPMandUVBats = false;
        @Config.Name("Batteries - Replace the Ultimate Battery with a MAX Battery")
        public boolean replaceUVwithMAXBat = false;

        @Config.Comment("This value determines the minimum amount of time in ticks a multiblock must be run for a single maintenance problem to have a change to occur. The default is 48 real-world hours.")
        @Config.RequiresMcRestart
        public int minimumMaintenanceTime = 3456000;

        @Config.Comment("Whether to enable maintenance. Multiblocks will still require maintenance hatches in their structures, however the involved mechanics are disabled.")
        public boolean enableMaintenance = true;
    }

    @Config.Comment("Config options of GTCE Bees features")
    public static GTBees GTBees = new GTBees();

    public static class GTBees {
        @Config.Comment("Enable/Disable all GT Bees features")
        public boolean EnableGTCEBees = true;

        @Config.Comment("Generate a recipe in the GT Centrifuge for every recipe in the Forestry Centrifuge")
        public boolean GenerateCentrifugeRecipes = true;

        @Config.Comment("Generate a recipe in the Fluid Extractor for every recipe in the Squeezer")
        public boolean GenerateExtractorRecipes = true;

        @Config.Comment("Add Autoclave recipes for the Combs")
        public boolean AutoclaverRecipes = true;

        @Config.Comment("Add Chemical Reactor recipes for the Combs")
        public boolean ReactorRecipes = true;

        @Config.Comment("Add Assembling Machine recipes for Impregnated items")
        public boolean AssemblerRecipes = true;
    }

    @Config.Comment("Config options for GregsConstruct features")
    public static GregsConstruct GregsConstruct = new GregsConstruct();

    public static class GregsConstruct {

        @Config.Comment("Enable/Disable all GregsConstruct features")
        @Config.Name("Enable Greg's Construct")
        @Config.RequiresMcRestart
        public boolean EnableGregsConstruct = true;

        @Config.Comment("Add Tools with GT Metals to Tinkers")
        @Config.Name("Tinker's metal tools")
        @Config.RequiresMcRestart
        public boolean TinkersMetalTools = true;

        @Config.Comment("Add Tools with GT Gems to Tinkers")
        @Config.Name("Tinker's gem tools")
        @Config.RequiresMcRestart
        public boolean TinkersGemTools = true;

        @Config.Comment("Add Smelting for GT Materials to Tinkers Smeltery")
        @Config.Name("Tinker's material smelting")
        @Config.RequiresMcRestart
        public boolean TinkersMaterialsSmelting = true;

        @Config.Comment("Add Alloying of GT Materials to Tinkers Smeltery")
        @Config.Name("Tinker's material alloying")
        @Config.RequiresMcRestart
        public boolean TinkersMaterialAlloying = true;

        @Config.Comment("Enable Glass recipe changes")
        @Config.Name("Greg's Construct glass processing")
        @Config.RequiresMcRestart
        public boolean GregsConstructGlassProcessing = true;

        @Config.Comment("Whether or not to register fluid solidification recipes for parts")
        @Config.Name("Fluid solidification recipes for parts")
        public boolean castingRecipes = true;
    }

    @Config.Comment("Config options for exNihilo features")
    public static ExNihilo exNihilo = new ExNihilo();

    public static class ExNihilo {
        public boolean Disable = false;
        public boolean highTierSieve = true;
    }

    @Config.Comment("Config options for Mystical Agriculture features.")
    public static MysticalAgriculture mysticalAgriculture = new MysticalAgriculture();

    public static class MysticalAgriculture {
        @Config.Comment("Disable all Mystical Agriculture integration features")
        @Config.Name("Disable Mystical Agriculture integration")
        @Config.RequiresMcRestart
        public boolean disable = false;
    }

    @Config.Comment("Config options for OpenComputers features.")
    public static OpenComputers openComputers = new OpenComputers();

    public static class OpenComputers {
        @Config.Comment("Disable all OpenComputers integration features")
        @Config.Name("Disable OpenComputers integration")
        @Config.RequiresMcRestart
        public boolean disable = false;
    }

    @Config.Comment("Config options of miscellaneous features")
    public static Misc Misc = new Misc();

    public static class Misc {

        @Config.Comment("Set this to false to disable the Forestry Integration")
        @Config.Name("Forestry's Ethanol and Seed Oil are used in recipes instead of GTCE's")
        public boolean ForestryIntegration = true;

        @Config.Comment("Set this to false to disable the high tier Air Collectors")
        @Config.Name("Air Collector have IV and LuV version")
        public boolean highTierCollector = true;

        @Config.Comment({"Sets HUD location", "1 - left-upper corner", "2 - right-upper corner", "3 - left-bottom corner", "4 - right-bottom corner"})
        public byte hudLocation = 1;
        @Config.Comment("Horizontal offset of HUD [0 ~ 100)")
        public byte hudOffsetX = 0;
        @Config.Comment("Vertical offset of HUD [0 ~ 100)")
        public byte hudOffsetY = 0;

        @Config.Comment("List of Soldering fluid [<fluid>:<amount>] amount=[1 ~ 64000]")
        @Config.RequiresMcRestart
        public String[] solderingFluidList = new String[]{"soldering_alloy:72", "tin:144", "lead:288"};

        @Config.Comment("Replace the normal thermal centrifuge recipes for purified ores with one that gives more crushed centrifuged and more byproduct material but with 20% more power hungry.")
        @Config.Name("Thermal Centrifuge ore doubling")
        @Config.RequiresMcRestart
        public boolean thermalCentrifugeOreProcessing = true;

        @Config.Comment("Add Chemical Bath recipes taking ores and UU-Matter as input and resulting in a larger amount of outputs.")
        @Config.Name("UU-Matter ore tripling")
        @Config.RequiresMcRestart
        public boolean uuMatterOreProcessing = true;


        @Config.Comment("Remove the standard electrolyzer recipes of Scheelite and Tungstate and add a more complex chemical purification process")
        @Config.Name("Tungsten Purification Process")
        @Config.RequiresMcRestart
        public boolean tungstenProcess = true;

        @Config.Comment("Assembly line can make LV to IV components cheaper (Motor, Pump, Conveyor, Piston, etc)")
        @Config.Name("Assembly Line make cheaper components")
        @Config.RequiresMcRestart
        public boolean assemblyLineMakeCheaperComponents = true;

        @Config.Comment("Assembler can make components (Motor, Pump, Conveyor, Piston, etc)")
        @Config.Name("Assembler can make components")
        @Config.RequiresMcRestart
        public boolean assemblerCanMakeComponents = true;

        @Config.Comment("Whether or not to add diminishing returns for GTCE Diesel Generators")
        @Config.Name("Diesel Generator efficiency loss")
        @Config.RequiresMcRestart
        public boolean dieselEfficiency = true;

        @Config.Comment("Whether or not to add diminishing returns for GTCE Steam Turbines")
        @Config.Name("Steam Turbine efficiency loss")
        @Config.RequiresMcRestart
        public boolean steamEfficiency = true;

        @Config.Comment("Whether or not to add diminishing returns for GTCE Gas Turbines")
        @Config.Name("Gas Turbine efficiency loss")
        @Config.RequiresMcRestart
        public boolean gasEfficiency = true;

        @Config.Comment("Whether or not to add diminishing returns for Gregicality Rocket Engines. Does not affect the Large Rocket Engine")
        @Config.Name("Rocket Engine efficiency loss")
        @Config.RequiresMcRestart
        public boolean rocketEfficiency = true;



        @Config.Comment("Whether or not to add diminishing returns for Gregicality Naquadah Reactors. Does not affect the Large Nauqadah Reactor")
        @Config.Name("Naquadah Reactor efficiency loss")
        @Config.RequiresMcRestart
        public boolean naqEfficieny = true;

        @Config.Comment("Change replication to be quicker or longer")
        @Config.Name("Replication time factor")
        @Config.RangeInt(min = 1)
        @Config.RequiresMcRestart
        public int replicationTimeFactor = 750;

        @Config.Name("Enable RockBreaker")
        @Config.RequiresMcRestart
        public boolean enableRockBreaker = true;
        @Config.Name("Enable RockBreaker high tier")
        @Config.RequiresMcRestart
        public boolean enableRockBreakerHighTier = true;

        @Config.Comment("Whether or not to generate all minecraft stones with lava flooding")
        @Config.Name("Generate minecraft stones with lava flow")
        @Config.RequiresMcRestart
        public boolean multiStoneGen = true;

        @Config.Comment("Whether or not to generate different ore variants. E.g. rich ores, pure ores and poor ores. If you disable this some of your worldgen will break! You will also break any previously created worlds!")
        @Config.Name("Add ore variants")
        @Config.RequiresMcRestart
        @Config.RequiresWorldRestart
        public boolean oreVariants = true;

        @Config.Comment("Whether or not to generate all stone types for ore variants. E.g. basalt rich ores, nether pure ores, etc. This will break existing worlds!")
        @Config.Name("Add ore variant stone types")
        @Config.RequiresMcRestart
        @Config.RequiresWorldRestart
        public boolean oreVariantsStoneTypes = true;

        @Config.Comment("Whether or not to enable machine disassembly recipes")
        @Config.Name("Enable disassembly")
        @Config.RequiresMcRestart
        public boolean enableDisassembly = true;

        @Config.Comment("Whether or not to have disassembly recipes with chanced outputs")
        @Config.Name("Disassembly chanced outputs")
        @Config.RequiresMcRestart
        public boolean disassemblyChancedOutputs = true;

        @Config.Comment({"Whether or not to log recipe removals",
        "Note that this should be enabled in development only"})
        @Config.Name("Recipe Removal Logging")
        @Config.RequiresMcRestart
        public boolean enableRecipeRemovalLogging = false;

        @Config.Comment({"Whether or not to have reverse engineered recipes (Disassembler, Electric Implosion, etc.) done before or after CT recipes.",
        "If True, they will be done after CT, meaning the reversed recipes are not touchable by scripts. If False, it is on the pack maker to fix the recipes manually."})
        @Config.Name("Reverse recipes after CT")
        @Config.RequiresMcRestart
        public boolean reverseAfterCT = true;

        @Config.Comment("Whether or not to make the recipes for Superconductors harder.")
        @Config.Name("Harder Superconductor Recipes")
        @Config.RequiresMcRestart
        public boolean harderSuperconductors = false;

    }

    @Config.Comment({"Configs for Armor and Tools", "Tiers are from ULV-0 to MAX-14"})
    public static Equipment equipment = new Equipment();

    public static class Equipment {
        @Config.Name("Nightvision Goggles")
        public NightvisionGoggles nightvisionGoggles = new NightvisionGoggles();
        @Config.Name("NanoMuscle Suit")
        public NanoSuit nanoSuit = new NanoSuit();
        @Config.Name("Advanced NanoMuscle Suit")
        public AdvNanoSuit advNanoSuit = new AdvNanoSuit();
        @Config.Name("QuarkTech Suit")
        public QuarkTechSuit quarkTechSuit = new QuarkTechSuit();
        @Config.Name("Advanced QuarkTech Suit")
        public AdvQuarkTechSuit advQuarkTechSuit = new AdvQuarkTechSuit();
        @Config.Name("Impeller Jetpack")
        public ImpellerJetpack impellerJetpack = new ImpellerJetpack();
        @Config.Name("Advanced Impeller Jetpack")
        public AdvImpellerJetpack advImpellerJetpack = new AdvImpellerJetpack();
        @Config.Name("Semifluid Jetpack")
        public SemiFluidJetpack semiFluidJetpack = new SemiFluidJetpack();
        @Config.Name("Batpack LV")
        public BatpackLv batpackLv = new BatpackLv();
        @Config.Name("Batpack MV")
        public BatpackMv batpackMv = new BatpackMv();
        @Config.Name("Batpack HV")
        public BatpackHv batpackHv = new BatpackHv();
        @Config.Name("Prospectors")
        public Prospector prospector = new Prospector();
    }

    public static class NightvisionGoggles {
        @Config.RangeInt(min = 0, max = 14)
        @Config.RequiresMcRestart
        public int voltageTier = 2;
        @Config.RangeInt(min = 0)
        @Config.RequiresMcRestart
        public int capacity = 400000;
        @Config.RangeInt(min = 0)
        @Config.RequiresMcRestart
        public int energyPerUse = 3600;
    }

    public static class NanoSuit {
        @Config.RangeInt(min = 0, max = 14)
        @Config.RequiresMcRestart
        public int voltageTier = 3;
        @Config.RangeInt(min = 0)
        @Config.RequiresMcRestart
        public int capacity = 1600000;
        @Config.RangeInt(min = 0)
        @Config.RequiresMcRestart
        public int energyPerUse = 5000;
    }

    public static class AdvNanoSuit {
        @Config.RangeInt(min = 0, max = 14)
        @Config.RequiresMcRestart
        public int voltageTier = 6;
        @Config.RangeInt(min = 0)
        @Config.RequiresMcRestart
        public int capacity = 11400000;
        @Config.RangeInt(min = 0)
        @Config.RequiresMcRestart
        public int energyPerUse = 5000;
    }

    public static class QuarkTechSuit {
        @Config.RangeInt(min = 0, max = 14)
        @Config.RequiresMcRestart
        public int voltageTier = 5;
        @Config.RangeInt(min = 0)
        @Config.RequiresMcRestart
        public int capacity = 8000000;
        @Config.RangeInt(min = 0)
        @Config.RequiresMcRestart
        public int energyPerUse = 10000;
    }

    public static class AdvQuarkTechSuit {
        @Config.RangeInt(min = 0, max = 14)
        @Config.RequiresMcRestart
        public int voltageTier = 6;
        @Config.RangeInt(min = 0)
        @Config.RequiresMcRestart
        public int capacity = 100000000;
        @Config.RangeInt(min = 0)
        @Config.RequiresMcRestart
        public int energyPerUse = 10000;
    }

    public static class ImpellerJetpack {
        @Config.RangeInt(min = 0, max = 14)
        @Config.RequiresMcRestart
        public int voltageTier = 3;
        @Config.RangeInt(min = 0)
        @Config.RequiresMcRestart
        public int capacity = 2520000;
        @Config.RangeInt(min = 0)
        @Config.RequiresMcRestart
        public int energyPerUse = 125;
    }

    public static class AdvImpellerJetpack {
        @Config.RangeInt(min = 0, max = 14)
        @Config.RequiresMcRestart
        public int voltageTier = 4;
        @Config.RangeInt(min = 0)
        @Config.RequiresMcRestart
        public int capacity = 11400000;
        @Config.RangeInt(min = 0)
        @Config.RequiresMcRestart
        public int energyPerUse = 512;
    }

    public static class SemiFluidJetpack {
        @Config.RangeInt(min = 0, max = 14)
        @Config.RequiresMcRestart
        public int voltageTier = 2;
        @Config.RangeInt(min = 0)
        @Config.RequiresMcRestart
        public int capacity = 12000;
    }

    public static class BatpackLv {
        @Config.RangeInt(min = 0, max = 14)
        @Config.RequiresMcRestart
        public int voltageTier = 1;
        @Config.RangeInt(min = 0)
        @Config.RequiresMcRestart
        public int capacity = 600000;
    }

    public static class BatpackMv {
        @Config.RangeInt(min = 0, max = 14)
        @Config.RequiresMcRestart
        public int voltageTier = 2;
        @Config.RangeInt(min = 0)
        @Config.RequiresMcRestart
        public int capacity = 2400000;
    }

    public static class BatpackHv {
        @Config.RangeInt(min = 0, max = 14)
        @Config.RequiresMcRestart
        public int voltageTier = 3;
        @Config.RangeInt(min = 0)
        @Config.RequiresMcRestart
        public int capacity = 9600000;
    }

    public static Multis multis = new Multis();

    public static class Multis {

        public VoidMiner voidMiner = new VoidMiner();
        public LargeMiner largeMiner = new LargeMiner();
        public Volcanus volcanus = new Volcanus();
        public CryogenicFreezer cryogenicFreezer = new CryogenicFreezer();
        public DistillationTower distillationTower = new DistillationTower();
        public LargeAssembler largeAssembler = new LargeAssembler();
        public LargeBenderAndForming largeBenderAndForming = new LargeBenderAndForming();
        public LargeCentrifuge largeCentrifuge = new LargeCentrifuge();
        public AdvancedChemicalReactor advancedChemicalReactor = new AdvancedChemicalReactor();
        public LargeCutting largeCutting = new LargeCutting();
        public LargeElectrolyzer largeElectrolyzer = new LargeElectrolyzer();
        public LargeExtruder largeExtruder = new LargeExtruder();
        public LargeForgeHammer largeForgeHammer = new LargeForgeHammer();
        public LargeMacerator largeMacerator = new LargeMacerator();
        public LargeMixer largeMixer = new LargeMixer();
        public LargeMultiUse largeMultiUse = new LargeMultiUse();
        public LargePackager largePackager = new LargePackager();
        public LargeSifter largeSifter = new LargeSifter();
        public LargeThermalCentrifuge largeThermalCentrifuge = new LargeThermalCentrifuge();
        public LargeWashingPlant largeWashingPlant = new LargeWashingPlant();
        public LargeWiremill largeWiremill = new LargeWiremill();
        public LargeBrewery largeBrewery = new LargeBrewery();
        public LargeElectromagnet largeElectromagnet = new LargeElectromagnet();
        public LargeExtractor largeExtractor = new LargeExtractor();
        public LargeArcFurnace largeArcFurnace = new LargeArcFurnace();
        public LargeCanningMachine largeCanningMachine = new LargeCanningMachine();
        public LargeMassFabricator largeMassFabricator = new LargeMassFabricator();
        public LargeReplicator largeReplicator = new LargeReplicator();
        public BatteryTower batteryTower = new BatteryTower();
        public AdvFusion advFusion = new AdvFusion();
        public LargeEngraver largeEngraver = new LargeEngraver();
        public HeatingCoils heatingCoils = new HeatingCoils();
        public SteamMultis steamMultis = new SteamMultis();
        public ProcessingArray processingArray = new ProcessingArray();
        public HyperReactors hyperReactors = new HyperReactors();
        public CentralMonitor centralMonitor = new CentralMonitor();
    }

        public static class LargeEngraver {
            @Config.Comment("The cost in percentage for a recipe's EU/t when run in the Large Engraver.")
            @Config.RangeInt(min = 1)
            @Config.Name("Large Engraver EU/t percentage cost")
            @Config.RequiresMcRestart
            public int euPercentage = 90;

            @Config.Comment("The amount of recipes processed at the same time per voltage tier difference.")
            @Config.RangeInt(min = 1)
            @Config.Name("Large Engraver parallel recipes per voltage tier difference")
            @Config.RequiresMcRestart
            public int stack = 1;

            @Config.Comment("The duration percentage of a recipe when done in the Large Engraver.")
            @Config.RangeInt(min = 1)
            @Config.Name("Large Engraver duration decrease percentage")
            @Config.RequiresMcRestart
            public int durationPercentage = 100;

            @Config.Comment("The boost given to chanced outputs for a recipe when run in the Large Engraver.")
            @Config.RangeInt(min = 1)
            @Config.Name("Large Engraver chanced output boost percentage")
            @Config.RequiresMcRestart
            public int chancedBoostPercentage = 100;
        }

        public static class AdvFusion {
            @Config.Comment("The percentage per tier above the tier required by the recipe to decrease the recipe duration.")
            @Config.Name("Advanced Fusion Coil duration discount")
            @Config.RangeDouble(min = 0.0, max = 0.99)
            @Config.RequiresMcRestart
            public double coilDurationDiscount = 0.05;

            @Config.Comment("The percentage per tier above the tier required by the recipe to increase the amount of coolant.")
            @Config.Name("Advanced Fusion Coil duration discount")
            @Config.RangeDouble(min = 0.0, max = 0.99)
            @Config.RequiresMcRestart
            public double vacuumCoolantIncrease = 0.05;

            @Config.Comment("The percentage per tier above the tier required by the recipe to decrease the EU/t.")
            @Config.Name("Advanced Fusion Coil duration discount")
            @Config.RangeDouble(min = 0.0, max = 0.99)
            @Config.RequiresMcRestart
            public double vacuumEnergyDecrease = 0.15;

            @Config.Comment("The percentage per tier above the tier required by the recipe to decrease the EU/t.")
            @Config.Name("Advanced Fusion Coil duration discount")
            @Config.RangeDouble(min = 0.0, max = 0.99)
            @Config.RequiresMcRestart
            public double divertorOutputIncrease = 0.10;

        }

        public static class BatteryTower {
            @Config.Comment("The base amount of energy a battery cell will hold. This is the amount the HV will hold, each tier above is multiplied by 4.")
            @Config.Name("Battery Tower cell base energy storage")
            @Config.RangeInt(min = 1, max = 2000000000)
            @Config.RequiresMcRestart
            public int baseCellCapacity = 25000000;

            @Config.Name("Battery Tower energy loss percentage")
            @Config.RangeInt(min = 0, max = 10000)
            @Config.RequiresMcRestart
            @Config.Comment("The percentage of EU of the Battery Tower's tier of voltage to loss every tick. E.g. if we are using MV cells, we lose 10% of 128, 12.8 rounded down to 12, every tick.")
            public int lossPercentage = 0;
        }

        public static class DistillationTower {
            @Config.Comment("The amount of parallel recipes the Advanced Distillation Tower will run if the recipe is a Distillation Tower recipe.")
            @Config.RangeInt(min = 1)
            @Config.Name("Distillation recipe multiplier")
            @Config.RequiresMcRestart
            public int distillationMultiplier = 8;

            @Config.Comment("The casing material to use for the Advanced Distllation Tower.")
            @Config.Name("Advanced Distillation Tower Casing Material")
            @Config.RequiresMcRestart
            public String casingMaterial = "babbitt_alloy";
        }

        public static class LargeAssembler {
            @Config.Comment("The cost in percentage for a recipe's EU/t when run in the Large Assembler.")
            @Config.RangeInt(min = 1)
            @Config.Name("Large Assembler EU/t percentage cost")
            @Config.RequiresMcRestart
            public int euPercentage = 90;

            @Config.Comment("The amount of recipes processed at the same time per voltage tier difference. E.g. recipe requires 32 EU/t, machine runs on IV power, then by default it will process 4 * 2 items per operation.")
            @Config.RangeInt(min = 1)
            @Config.Name("Large Assembler parallel recipes per voltage tier difference")
            @Config.RequiresMcRestart
            public int stack = 2;

            @Config.Comment("The duration percentage of a recipe when done in the Large Assembler.")
            @Config.RangeInt(min = 1)
            @Config.Name("Large Assembler duration decrease percentage")
            @Config.RequiresMcRestart
            public int durationPercentage = 200;

            @Config.Comment("The boost given to chanced outputs for a recipe when run in the Large Assembler.")
            @Config.RangeInt(min = 1)
            @Config.Name("Large Assembler chanced output boost percentage")
            @Config.RequiresMcRestart
            public int chancedBoostPercentage = 100;
        }

        public static class LargeBenderAndForming {
            @Config.Comment("The cost in percentage for a recipe's EU/t when run in the Large Bender And Forming.")
            @Config.RangeInt(min = 1)
            @Config.Name("Large Bender And Forming EU/t percentage cost")
            @Config.RequiresMcRestart
            public int euPercentage = 90;

            @Config.Comment("The amount of recipes processed at the same time per voltage tier difference.")
            @Config.RangeInt(min = 1)
            @Config.Name("Large Bender And Forming parallel recipes per voltage tier difference")
            @Config.RequiresMcRestart
            public int stack = 4;

            @Config.Comment("The duration percentage of a recipe when done in the Large Bender and Forming.")
            @Config.RangeInt(min = 1)
            @Config.Name("Large Bender And Forming duration decrease percentage")
            @Config.RequiresMcRestart
            public int durationPercentage = 250;

            @Config.Comment("The boost given to chanced outputs for a recipe when run in the Large Bender And Forming.")
            @Config.RangeInt(min = 1)
            @Config.Name("Large Bender And Forming chanced output boost percentage")
            @Config.RequiresMcRestart
            public int chancedBoostPercentage = 100;

            @Config.Comment("The casing material to use for the Large Centrifuge.")
            @Config.Name("Large Bending and Forming casing material")
            @Config.RequiresMcRestart
            public String casingMaterial = "titanium";
        }

        public static class LargeCentrifuge {
            @Config.Comment("The cost in percentage for a recipe's EU/t when run in the Large Centrifuge.")
            @Config.RangeInt(min = 1)
            @Config.Name("Large Centrifuge EU/t percentage cost")
            @Config.RequiresMcRestart
            public int euPercentage = 80;

            @Config.Comment("The amount of recipes processed at the same time per voltage tier difference.")
            @Config.RangeInt(min = 1)
            @Config.Name("Large Centrifuge parallel recipes per voltage tier difference")
            @Config.RequiresMcRestart
            public int stack = 6;

            @Config.Comment("The duration percentage of a recipe when done in the Large Centrifuge.")
            @Config.RangeInt(min = 1)
            @Config.Name("Large Centrifuge duration decrease percentage")
            @Config.RequiresMcRestart
            public int durationPercentage = 333;

            @Config.Comment("The boost given to chanced outputs for a recipe when run in the Large Centrifuge.")
            @Config.RangeInt(min = 1)
            @Config.Name("Large Centrifuge chanced output boost percentage")
            @Config.RequiresMcRestart
            public int chancedBoostPercentage = 100;


            @Config.Comment("The casing material to use for the Large Centrifuge.")
            @Config.Name("Large Centrifuge casing material")
            @Config.RequiresMcRestart
            public String casingMaterial = "tumbaga";
        }

        public static class AdvancedChemicalReactor {
            @Config.Comment("The cost in percentage for a recipe's EU/t when run in the Advanced Chemical Reactor.")
            @Config.RangeInt(min = 1)
            @Config.Name("Advanced Chemical Reactor EU/t percentage cost")
            @Config.RequiresMcRestart
            public int euPercentage = 50;

            @Config.Comment("The amount of recipes processed at the same time per voltage tier difference.")
            @Config.RangeInt(min = 1)
            @Config.Name("Advanced Chemical Reactor parallel recipes per voltage tier difference")
            @Config.RequiresMcRestart
            public int stack = 2;

            @Config.Comment("The duration percentage of a recipe when done in the Advanced Chemical Reactor.")
            @Config.RangeInt(min = 1)
            @Config.Name("Advanced Chemical Reactor duration decrease percentage")
            @Config.RequiresMcRestart
            public int durationPercentage = 100;

            @Config.Comment("The boost given to chanced outputs for a recipe when run in the Advanced Chemical Reactor.")
            @Config.RangeInt(min = 1)
            @Config.Name("Advanced Chemical Reactor chanced output boost percentage")
            @Config.RequiresMcRestart
            public int chancedBoostPercentage = 100;

        }

        public static class LargeCutting {
            @Config.Comment("The cost in percentage for a recipe's EU/t when run in the Large Cutting.")
            @Config.RangeInt(min = 1)
            @Config.Name("Large Cutting EU/t percentage cost")
            @Config.RequiresMcRestart
            public int euPercentage = 80;

            @Config.Comment("The amount of recipes processed at the same time per voltage tier difference.")
            @Config.RangeInt(min = 1)
            @Config.Name("Large Cutting parallel recipes per voltage tier difference")
            @Config.RequiresMcRestart
            public int stack = 2;

            @Config.Comment("The duration percentage of a recipe when done in the Large Cutting Machine.")
            @Config.RangeInt(min = 1)
            @Config.Name("Large Cutting duration decrease percentage")
            @Config.RequiresMcRestart
            public int durationPercentage = 166;

            @Config.Comment("The boost given to chanced outputs for a recipe when run in the Large Cutting.")
            @Config.RangeInt(min = 1)
            @Config.Name("Large Cutting chanced output boost percentage")
            @Config.RequiresMcRestart
            public int chancedBoostPercentage = 100;

            @Config.Comment("The casing material to use for the Large Centrifuge.")
            @Config.Name("Large Cutting Machine casing material")
            @Config.RequiresMcRestart
            public String casingMaterial = "maraging_steel_250";
        }

        public static class LargeElectrolyzer {
            @Config.Comment("The cost in percentage for a recipe's EU/t when run in the Large Electrolyzer.")
            @Config.RangeInt(min = 1)
            @Config.Name("Large Electrolyzer EU/t percentage cost")
            @Config.RequiresMcRestart
            public int euPercentage = 90;

            @Config.Comment("The amount of recipes processed at the same time per voltage tier difference.")
            @Config.RangeInt(min = 1)
            @Config.Name("Large Electrolyzer parallel recipes per voltage tier difference")
            @Config.RequiresMcRestart
            public int stack = 2;

            @Config.Comment("The duration percentage of a recipe when done in the Large Electrolyzer.")
            @Config.RangeInt(min = 1)
            @Config.Name("Large Electrolyzer duration decrease percentage")
            @Config.RequiresMcRestart
            public int durationPercentage = 200;

            @Config.Comment("The boost given to chanced outputs for a recipe when run in the Large Electrolyzer.")
            @Config.RangeInt(min = 1)
            @Config.Name("Large Electrolyzer chanced output boost percentage")
            @Config.RequiresMcRestart
            public int chancedBoostPercentage = 100;

            @Config.Comment("The casing material to use for the Large Centrifuge.")
            @Config.Name("Large Electrolyzer casing material")
            @Config.RequiresMcRestart
            public String casingMaterial = "potin";
        }

        public static class LargeExtruder {
            @Config.Comment("The cost in percentage for a recipe's EU/t when run in the Large Extruder.")
            @Config.RangeInt(min = 1)
            @Config.Name("Large Extruder EU/t percentage cost")
            @Config.RequiresMcRestart
            public int euPercentage = 90;

            @Config.Comment("The amount of recipes processed at the same time per voltage tier difference.")
            @Config.RangeInt(min = 1)
            @Config.Name("Large Extruder parallel recipes per voltage tier difference")
            @Config.RequiresMcRestart
            public int stack = 6;

            @Config.Comment("The duration percentage of a recipe when done in the Large Extruder.")
            @Config.RangeInt(min = 1)
            @Config.Name("Large Extruder duration decrease percentage")
            @Config.RequiresMcRestart
            public int durationPercentage = 250;

            @Config.Comment("The boost given to chanced outputs for a recipe when run in the Large Extruder.")
            @Config.RangeInt(min = 1)
            @Config.Name("Large Extruder chanced output boost percentage")
            @Config.RequiresMcRestart
            public int chancedBoostPercentage = 100;

            @Config.Comment("The casing material to use for the Large Centrifuge.")
            @Config.Name("Large Extruder casing material")
            @Config.RequiresMcRestart
            public String casingMaterial = "inconel_625";
        }

        public static class LargeForgeHammer {
            @Config.Comment("The cost in percentage for a recipe's EU/t when run in the Large Forge Hammer.")
            @Config.RangeInt(min = 1)
            @Config.Name("Large Forge Hammer EU/t percentage cost")
            @Config.RequiresMcRestart
            public int euPercentage = 90;

            @Config.Comment("The amount of recipes processed at the same time per voltage tier difference.")
            @Config.RangeInt(min = 1)
            @Config.Name("Large Forge Hammer parallel recipes per voltage tier difference")
            @Config.RequiresMcRestart
            public int stack = 4;

            @Config.Comment("The duration percentage of a recipe when done in the Large Forge Hammer.")
            @Config.RangeInt(min = 1)
            @Config.Name("Large Forge Hammer duration decrease percentage")
            @Config.RequiresMcRestart
            public int durationPercentage = 125;

            @Config.Comment("The boost given to chanced outputs for a recipe when run in the Large Forge Hammer.")
            @Config.RangeInt(min = 1)
            @Config.Name("Large Forge Hammer chanced output boost percentage")
            @Config.RequiresMcRestart
            public int chancedBoostPercentage = 100;

            @Config.Comment("The casing material to use for the Large Centrifuge.")
            @Config.Name("Large Forge Hammer casing material")
            @Config.RequiresMcRestart
            public String casingMaterial = "iron";
        }

        public static class LargeMacerator {
            @Config.Comment("The cost in percentage for a recipe's EU/t when run in the Large Macerator.")
            @Config.RangeInt(min = 1)
            @Config.Name("Large Macerator EU/t percentage cost")
            @Config.RequiresMcRestart
            public int euPercentage = 90;

            @Config.Comment("The amount of recipes processed at the same time per voltage tier difference.")
            @Config.RangeInt(min = 1)
            @Config.Name("Large Macerator parallel recipes per voltage tier difference")
            @Config.RequiresMcRestart
            public int stack = 10;

            @Config.Comment("The duration percentage of a recipe when done in the Large Macerator.")
            @Config.RangeInt(min = 1)
            @Config.Name("Large Macerator duration decrease percentage")
            @Config.RequiresMcRestart
            public int durationPercentage = 500;

            @Config.Comment("The boost given to chanced outputs for a recipe when run in the Large Macerator.")
            @Config.RangeInt(min = 1)
            @Config.Name("Large Macerator chanced output boost percentage")
            @Config.RequiresMcRestart
            public int chancedBoostPercentage = 200;

            @Config.Comment("The casing material to use for the Large Centrifuge.")
            @Config.Name("Large Macerator casing material")
            @Config.RequiresMcRestart
            public String casingMaterial = "stellite";
        }

        public static class LargeMixer {
            @Config.Comment("The cost in percentage for a recipe's EU/t when run in the Large Mixer.")
            @Config.RangeInt(min = 1)
            @Config.Name("Large Mixer EU/t percentage cost")
            @Config.RequiresMcRestart
            public int euPercentage = 90;

            @Config.Comment("The amount of recipes processed at the same time per voltage tier difference.")
            @Config.RangeInt(min = 1)
            @Config.Name("Large Mixer parallel recipes per voltage tier difference")
            @Config.RequiresMcRestart
            public int stack = 8;

            @Config.Comment("The duration percentage of a recipe when done in the Large Mixer.")
            @Config.RangeInt(min = 1)
            @Config.Name("Large Mixer duration decrease percentage")
            @Config.RequiresMcRestart
            public int durationPercentage = 333;

            @Config.Comment("The boost given to chanced outputs for a recipe when run in the Large Mixer.")
            @Config.RangeInt(min = 1)
            @Config.Name("Large Mixer chanced output boost percentage")
            @Config.RequiresMcRestart
            public int chancedBoostPercentage = 100;

            @Config.Comment("The casing material to use for the Large Centrifuge.")
            @Config.Name("Large Mixer casing material")
            @Config.RequiresMcRestart
            public String casingMaterial = "staballoy";
        }

        public static class LargeMultiUse {
            @Config.Comment("The cost in percentage for a recipe's EU/t when run in the Large Multi Use.")
            @Config.RangeInt(min = 1)
            @Config.Name("Large Multi Use EU/t percentage cost")
            @Config.RequiresMcRestart
            public int euPercentage = 80;

            @Config.Comment("The amount of recipes processed at the same time per voltage tier difference.")
            @Config.RangeInt(min = 1)
            @Config.Name("Large Multi Use parallel recipes per voltage tier difference")
            @Config.RequiresMcRestart
            public int stack = 4;

            @Config.Comment("The duration percentage of a recipe when done in the Large Multiuse.")
            @Config.RangeInt(min = 1)
            @Config.Name("Large Multi Use duration decrease percentage")
            @Config.RequiresMcRestart
            public int durationPercentage = 200;

            @Config.Comment("The boost given to chanced outputs for a recipe when run in the Large Multi Use.")
            @Config.RangeInt(min = 1)
            @Config.Name("Large Multi Use chanced output boost percentage")
            @Config.RequiresMcRestart
            public int chancedBoostPercentage = 100;

            @Config.Comment("The casing material to use for the Large Centrifuge.")
            @Config.Name("Large Multiuse casing material")
            @Config.RequiresMcRestart
            public String casingMaterial = "staballoy";
        }

        public static class LargeSifter {
            @Config.Comment("The cost in percentage for a recipe's EU/t when run in the Large Sifter.")
            @Config.RangeInt(min = 1)
            @Config.Name("Large Sifter EU/t percentage cost")
            @Config.RequiresMcRestart
            public int euPercentage = 75;

            @Config.Comment("The amount of recipes processed at the same time per voltage tier difference.")
            @Config.RangeInt(min = 1)
            @Config.Name("Large Sifter parallel recipes per voltage tier difference")
            @Config.RequiresMcRestart
            public int stack = 4;

            @Config.Comment("The duration percentage of a recipe when done in the Large Sifter.")
            @Config.RangeInt(min = 1)
            @Config.Name("Large Sifter duration decrease percentage")
            @Config.RequiresMcRestart
            public int durationPercentage = 225;

            @Config.Comment("The boost given to chanced outputs for a recipe when run in the Large Sifter.")
            @Config.RangeInt(min = 1)
            @Config.Name("Large Sifter chanced output boost percentage")
            @Config.RequiresMcRestart
            public int chancedBoostPercentage = 200;

            @Config.Comment("The casing material to use for the Large Centrifuge.")
            @Config.Name("Large Sifter casing material")
            @Config.RequiresMcRestart
            public String casingMaterial = "eglin_steel";
        }

        public static class LargeThermalCentrifuge {
            @Config.Comment("The cost in percentage for a recipe's EU/t when run in the Large Thermal Centrifuge.")
            @Config.RangeInt(min = 1)
            @Config.Name("Large Thermal Centrifuge EU/t percentage cost")
            @Config.RequiresMcRestart
            public int euPercentage = 80;

            @Config.Comment("The amount of recipes processed at the same time per voltage tier difference.")
            @Config.RangeInt(min = 1)
            @Config.Name("Large Thermal Centrifuge parallel recipes per voltage tier difference")
            @Config.RequiresMcRestart
            public int stack = 8;

            @Config.Comment("The duration percentage of a recipe when done in the Large Thermal Centrifuge.")
            @Config.RangeInt(min = 1)
            @Config.Name("Large Thermal Centrifuge duration decrease percentage")
            @Config.RequiresMcRestart
            public int durationPercentage = 200;

            @Config.Comment("The boost given to chanced outputs for a recipe when run in the Large Thermal Centrifuge.")
            @Config.RangeInt(min = 1)
            @Config.Name("Large Thermal Centrifuge chanced output boost percentage")
            @Config.RequiresMcRestart
            public int chancedBoostPercentage = 150;

            @Config.Comment("The casing material to use for the Large Centrifuge.")
            @Config.Name("Large Centrifuge casing material")
            @Config.RequiresMcRestart
            public String casingMaterial = "red_steel";
        }

        public static class LargeWashingPlant {
            @Config.Comment("The cost in percentage for a recipe's EU/t when run in the Large Washing Plant.")
            @Config.RangeInt(min = 1)
            @Config.Name("Large Washing Plant EU/t percentage cost")
            @Config.RequiresMcRestart
            public int euPercentage = 90;

            @Config.Comment("The amount of recipes processed at the same time per voltage tier difference.")
            @Config.RangeInt(min = 1)
            @Config.Name("Large Washing Plant parallel recipes per voltage tier difference")
            @Config.RequiresMcRestart
            public int stack = 6;

            @Config.Comment("The duration percentage of a recipe when done in the Large Washing Plant.")
            @Config.RangeInt(min = 1)
            @Config.Name("Large Washing Plant duration decrease percentage")
            @Config.RequiresMcRestart
            public int durationPercentage = 333;

            @Config.Comment("The boost given to chanced outputs for a recipe when run in the Large Washing Plant.")
            @Config.RangeInt(min = 1)
            @Config.Name("Large Washing Plant chanced output boost percentage")
            @Config.RequiresMcRestart
            public int chancedBoostPercentage = 100;

            @Config.Comment("The casing material to use for the Large Centrifuge.")
            @Config.Name("Large Washing Plant casing material")
            @Config.RequiresMcRestart
            public String casingMaterial = "grisium";
        }

        public static class LargeWiremill {
            @Config.Comment("The cost in percentage for a recipe's EU/t when run in the Large Wiremill.")
            @Config.RangeInt(min = 1)
            @Config.Name("Large Wiremill EU/t percentage cost")
            @Config.RequiresMcRestart
            public int euPercentage = 80;

            @Config.Comment("The amount of recipes processed at the same time per voltage tier difference.")
            @Config.RangeInt(min = 1)
            @Config.Name("Large Wiremill parallel recipes per voltage tier difference")
            @Config.RequiresMcRestart
            public int stack = 6;

            @Config.Comment("The duration percentage of a recipe when done in the Large Wiremill.")
            @Config.RangeInt(min = 1)
            @Config.Name("Large Wiremill duration decrease percentage")
            @Config.RequiresMcRestart
            public int durationPercentage = 333;

            @Config.Comment("The boost given to chanced outputs for a recipe when run in the Large Wiremill.")
            @Config.RangeInt(min = 1)
            @Config.Name("Large Wiremill chanced output boost percentage")
            @Config.RequiresMcRestart
            public int chancedBoostPercentage = 100;

            @Config.Comment("The casing material to use for the Large Centrifuge.")
            @Config.Name("Large Wiremill casing material")
            @Config.RequiresMcRestart
            public String casingMaterial = "maraging_steel_250";
        }

    public static class LargeBrewery {
        @Config.Comment("The cost in percentage for a recipe's EU/t when run in the Large Brewery.")
        @Config.RangeInt(min = 1)
        @Config.Name("Large Brewery EU/t percentage cost")
        @Config.RequiresMcRestart
        public int euPercentage = 80;

        @Config.Comment("The amount of recipes processed at the same time per voltage tier difference.")
        @Config.RangeInt(min = 1)
        @Config.Name("Large Brewery parallel recipes per voltage tier difference")
        @Config.RequiresMcRestart
        public int stack = 8;

        @Config.Comment("The duration percentage of a recipe when done in the Large Brewery.")
        @Config.RangeInt(min = 1)
        @Config.Name("Large Brewery duration decrease percentage")
        @Config.RequiresMcRestart
        public int durationPercentage = 200;

        @Config.Comment("The boost given to chanced outputs for a recipe when run in the Large Brewery.")
        @Config.RangeInt(min = 1)
        @Config.Name("Large Brewery chanced output boost percentage")
        @Config.RequiresMcRestart
        public int chancedBoostPercentage = 100;

        @Config.Comment("The casing material to use for the Large Brewery.")
        @Config.Name("Large Brewery casing material")
        @Config.RequiresMcRestart
        public String casingMaterial = "grisium";
    }

    public static class LargeElectromagnet {
        @Config.Comment("The cost in percentage for a recipe's EU/t when run in the Large Electromagnet.")
        @Config.RangeInt(min = 1)
        @Config.Name("Large Electromagnet EU/t percentage cost")
        @Config.RequiresMcRestart
        public int euPercentage = 80;

        @Config.Comment("The amount of recipes processed at the same time per voltage tier difference.")
        @Config.RangeInt(min = 1)
        @Config.Name("Large Electromagnet parallel recipes per voltage tier difference")
        @Config.RequiresMcRestart
        public int stack = 16;

        @Config.Comment("The duration percentage of a recipe when done in the Large Electromagnet.")
        @Config.RangeInt(min = 1)
        @Config.Name("Large Electromagnet duration decrease percentage")
        @Config.RequiresMcRestart
        public int durationPercentage = 400;

        @Config.Comment("The boost given to chanced outputs for a recipe when run in the Large Electromagnet.")
        @Config.RangeInt(min = 1)
        @Config.Name("Large Electromagnet chanced output boost percentage")
        @Config.RequiresMcRestart
        public int chancedBoostPercentage = 100;

        @Config.Comment("The casing material to use for the Large Electromagnet.")
        @Config.Name("Large Electromagnet casing material")
        @Config.RequiresMcRestart
        public String casingMaterial = "babbitt_alloy";
    }

    public static class LargeExtractor {
        @Config.Comment("The cost in percentage for a recipe's EU/t when run in the Large Extractor.")
        @Config.RangeInt(min = 1)
        @Config.Name("Large Extractor EU/t percentage cost")
        @Config.RequiresMcRestart
        public int euPercentage = 75;

        @Config.Comment("The amount of recipes processed at the same time per voltage tier difference.")
        @Config.RangeInt(min = 1)
        @Config.Name("Large Extractor parallel recipes per voltage tier difference")
        @Config.RequiresMcRestart
        public int stack = 16;

        @Config.Comment("The duration percentage of a recipe when done in the Large Extractor.")
        @Config.RangeInt(min = 1)
        @Config.Name("Large Extractor duration decrease percentage")
        @Config.RequiresMcRestart
        public int durationPercentage = 400;

        @Config.Comment("The boost given to chanced outputs for a recipe when run in the Large Extractor.")
        @Config.RangeInt(min = 1)
        @Config.Name("Large Extractor chanced output boost percentage")
        @Config.RequiresMcRestart
        public int chancedBoostPercentage = 100;

        @Config.Comment("The casing material to use for the Large Extractor.")
        @Config.Name("Large Extractor casing material")
        @Config.RequiresMcRestart
        public String casingMaterial = "talonite";
    }

    public static class LargeArcFurnace {
        @Config.Comment("The cost in percentage for a recipe's EU/t when run in the Large Arc Furnace.")
        @Config.RangeInt(min = 1)
        @Config.Name("Large Arc Furnace EU/t percentage cost")
        @Config.RequiresMcRestart
        public int euPercentage = 80;

        @Config.Comment("The amount of recipes processed at the same time per voltage tier difference.")
        @Config.RangeInt(min = 1)
        @Config.Name("Large Arc Furnace parallel recipes per voltage tier difference")
        @Config.RequiresMcRestart
        public int stack = 4;

        @Config.Comment("The duration percentage of a recipe when done in the Large Arc Furnace.")
        @Config.RangeInt(min = 1)
        @Config.Name("Large Arc Furnace duration decrease percentage")
        @Config.RequiresMcRestart
        public int durationPercentage = 200;

        @Config.Comment("The boost given to chanced outputs for a recipe when run in the Large Arc Furnace.")
        @Config.RangeInt(min = 1)
        @Config.Name("Large Arc Furnace chanced output boost percentage")
        @Config.RequiresMcRestart
        public int chancedBoostPercentage = 100;

        @Config.Comment("The casing material to use for the Large Arc Furnace.")
        @Config.Name("Large Arc Furnace casing material")
        @Config.RequiresMcRestart
        public String casingMaterial = "invar";
    }

    public static class LargeCanningMachine {
        @Config.Comment("The cost in percentage for a recipe's EU/t when run in the Large Canning Machine.")
        @Config.RangeInt(min = 1)
        @Config.Name("Large Canning Machine EU/t percentage cost")
        @Config.RequiresMcRestart
        public int euPercentage = 80;

        @Config.Comment("The amount of recipes processed at the same time per voltage tier difference.")
        @Config.RangeInt(min = 1)
        @Config.Name("Large Canning Machine parallel recipes per voltage tier difference")
        @Config.RequiresMcRestart
        public int stack = 4;

        @Config.Comment("The duration percentage of a recipe when done in the Large Canning Machine.")
        @Config.RangeInt(min = 1)
        @Config.Name("Large Canning Machine duration decrease percentage")
        @Config.RequiresMcRestart
        public int durationPercentage = 100;

        @Config.Comment("The boost given to chanced outputs for a recipe when run in the Large Canning Machine.")
        @Config.RangeInt(min = 1)
        @Config.Name("Large Canning Machine chanced output boost percentage")
        @Config.RequiresMcRestart
        public int chancedBoostPercentage = 100;

        @Config.Comment("The casing material to use for the Large Canning Machine.")
        @Config.Name("Large Canning Machine casing material")
        @Config.RequiresMcRestart
        public String casingMaterial = "steel";
    }

    public static class LargeMassFabricator {
        @Config.Comment("The cost in percentage for a recipe's EU/t when run in the Large Mass Fabricator.")
        @Config.RangeInt(min = 1)
        @Config.Name("Large Mass Fabricator EU/t percentage cost")
        @Config.RequiresMcRestart
        public int euPercentage = 100;

        @Config.Comment("The amount of recipes processed at the same time per voltage tier difference.")
        @Config.RangeInt(min = 0)
        @Config.Name("Large Mass Fabricator parallel recipes per voltage tier difference")
        @Config.RequiresMcRestart
        public int stack = 0;

        @Config.Comment("The duration percentage of a recipe when done in the Large Mass Fabricator.")
        @Config.RangeInt(min = 1)
        @Config.Name("Large Mass Fabricator duration decrease percentage")
        @Config.RequiresMcRestart
        public int durationPercentage = 100;

        @Config.Comment("The boost given to chanced outputs for a recipe when run in the Large Mass Fabricator.")
        @Config.RangeInt(min = 1)
        @Config.Name("Large Mass Fabricator chanced output boost percentage")
        @Config.RequiresMcRestart
        public int chancedBoostPercentage = 100;

        @Config.Comment("The casing material to use for the Large Mass Fabricator.")
        @Config.Name("Large Mass Fabricator casing material")
        @Config.RequiresMcRestart
        public String casingMaterial = "tritanium";
    }

    public static class LargeReplicator {
        @Config.Comment("The cost in percentage for a recipe's EU/t when run in the Large Mass Fabricator.")
        @Config.RangeInt(min = 1)
        @Config.Name("Large Mass Fabricator EU/t percentage cost")
        @Config.RequiresMcRestart
        public int euPercentage = 100;

        @Config.Comment("The amount of recipes processed at the same time per voltage tier difference.")
        @Config.RangeInt(min = 0)
        @Config.Name("Large Mass Fabricator parallel recipes per voltage tier difference")
        @Config.RequiresMcRestart
        public int stack = 0;

        @Config.Comment("The duration percentage of a recipe when done in the Large Mass Fabricator.")
        @Config.RangeInt(min = 1)
        @Config.Name("Large Mass Fabricator duration decrease percentage")
        @Config.RequiresMcRestart
        public int durationPercentage = 100;

        @Config.Comment("The boost given to chanced outputs for a recipe when run in the Large Mass Fabricator.")
        @Config.RangeInt(min = 1)
        @Config.Name("Large Mass Fabricator chanced output boost percentage")
        @Config.RequiresMcRestart
        public int chancedBoostPercentage = 100;

        @Config.Comment("The casing material to use for the Large Mass Fabricator.")
        @Config.Name("Large Mass Fabricator casing material")
        @Config.RequiresMcRestart
        public String casingMaterial = "tritanium";
    }

        public static class VoidMiner {
            @Config.Comment("The maximum temperature the void miner can reach before overheating. Every second the void miner will generate 10 different ores with amount between 1 and (temperature/1000)^2 ores. default: [9000]")
            @Config.RangeInt(min = 1000)
            @Config.RequiresMcRestart
            @Config.Name("Void Miner I max temperature")
            public int maxTemp = 9000;

            @Config.Comment("The maximum temperature the void miner can reach before overheating. Every second the void miner will generate 10 different ores with amount between 1 and (temperature/1000)^2 ores. default: [9000]")
            @Config.RangeInt(min = 1000)
            @Config.RequiresMcRestart
            @Config.Name("Void Miner II max temperature")
            public int maxTempUHV = 16000;

            @Config.Comment("The maximum temperature the void miner can reach before overheating. Every second the void miner will generate 10 different ores with amount between 1 and (temperature/1000)^2 ores. default: [9000]")
            @Config.RangeInt(min = 1000)
            @Config.RequiresMcRestart
            @Config.Name("Void Miner III max temperature")
            public int maxTempUEV = 25000;

            @Config.Comment("Whether or not to add all ore variants to the Void Miner's ore table. If false only the first ore in the material's ore dictionary will be added.")
            @Config.RequiresMcRestart
            @Config.Name("Void miner ore variants")
            public boolean oreVariants = true;

            @Config.Comment("The name of the ores to blacklist for the MK1 Void Miner")
            @Config.RequiresMcRestart
            @Config.Name("MK1 Void Miner Blacklist")
            public String[] oreBlacklist = new String[]{"trinium"};

            @Config.Comment("The name of the ores to blacklist for the MK2 Void Miner")
            @Config.RequiresMcRestart
            @Config.Name("MK2 Void Miner Blacklist")
            public String[] oreBlacklistUHV = new String[]{""};

            @Config.Comment("The name of the ores to blacklist for the MK3 Void Miner")
            @Config.RequiresMcRestart
            @Config.Name("MK3 Void Miner Blacklist")
            public String[] oreBlacklistUEV = new String[]{""};

            @Config.Comment("The name of items you wish to add to the MK1 Void Miner. Example: \"minecraft:wool:2\"")
            @Config.RequiresMcRestart
            @Config.Name("MK1 Void Miner Whitelist")
            public String[] oreWhitelist = new String[]{""};

            @Config.Comment("The name of items you wish to add to the MK2 Void Miner")
            @Config.RequiresMcRestart
            @Config.Name("MK2 Void Miner Whitelist")
            public String[] oreWhitelistUHV = new String[]{""};

            @Config.Comment("The name of items you wish to add to the MK3 Void Miner")
            @Config.RequiresMcRestart
            @Config.Name("MK3 Void Miner Whitelist")
            public String[] oreWhitelistUEV = new String[]{""};
        }

        public static class LargeMiner {

            @Config.Comment("The length in chunks of the side of the square centered on the Miner that will be mined.")
            @Config.RangeInt(min = 1)
            @Config.RequiresMcRestart
            @Config.Name("Basic Large Miner chunk diameter")
            public int basicMinerDiameter = 3;

            @Config.Comment("The level of fortune which will be applied to blocks that the Miner mines.")
            @Config.RangeInt(min = 0, max = 100)
            @Config.RequiresMcRestart
            @Config.Name("Basic Large Miner fortune level")
            public int basicMinerFortune = 3;

            @Config.Comment("The name of the material to use as casing.")
            @Config.RequiresMcRestart
            @Config.Name("Basic Miner casing material")
            public String basicMinerCasingMaterial = "black_steel";

            @Config.Comment("The length in chunks of the side of the square centered on the Miner that will be mined.")
            @Config.RangeInt(min = 1)
            @Config.RequiresMcRestart
            @Config.Name("Large Miner chunk diameter")
            public int largeMinerDiameter = 5;

            @Config.Comment("The level of fortune which will be applied to blocks that the Miner mines.")
            @Config.RangeInt(min = 0, max = 100)
            @Config.RequiresMcRestart
            @Config.Name("Large Miner fortune level")
            public int largeMinerFortune = 6;

            @Config.Comment("The name of the material to use as casing.")
            @Config.RequiresMcRestart
            @Config.Name("Large Miner casing material")
            public String largeMinerCasingMaterial = "hss_g";

            @Config.Comment("The length in chunks of the side of the square centered on the Miner that will be mined.")
            @Config.RangeInt(min = 1)
            @Config.RequiresMcRestart
            @Config.Name("Advanced Large Miner chunk diameter")
            public int advancedMinerDiameter = 7;

            @Config.Comment("The level of fortune which will be applied to blocks that the Miner mines.")
            @Config.RangeInt(min = 0, max = 100)
            @Config.RequiresMcRestart
            @Config.Name("Advanced Large Miner fortune level")
            public int advancedMinerFortune = 9;

            @Config.Comment("The name of the material to use as casing.")
            @Config.RequiresMcRestart
            @Config.Name("Advanced Miner casing material")
            public String advancedMinerCasingMaterial = "hss_s";

        }

        public static class Volcanus {
            @Config.Comment("The duration percentage of a recipe when done in the Volcanus.")
            @Config.RangeInt(min = 1)
            @Config.RequiresMcRestart
            @Config.Name("Volcanus recipe duration decrease factor")
            public int durationDecreasePercentage = 66;

            @Config.Comment("The amount by which the EU/t for recipes in the Volanus is decreased. E.g. EU/t * 0.75.")
            @Config.RangeInt(min = 1, max = 100)
            @Config.RequiresMcRestart
            @Config.Name("Volcanus recipe EU/t discount")
            public int energyDecreasePercentage = 90;

        }

        public static class CryogenicFreezer {
            @Config.Comment("The amount by which the EU/t for recipes in the Cryogenic Freezer is decreased. E.g. EU/t * 0.75.")
            @Config.RangeInt(min = 1, max = 100)
            @Config.RequiresMcRestart
            @Config.Name("Cryogenic Freezer recipe EU/t discount")
            public int energyDecreasePercentage = 90;

            @Config.Comment("The duration percentage of a recipe when done in the Cryogenic Freezer.")
            @Config.RangeInt(min = 1)
            @Config.RequiresMcRestart
            @Config.Name("Cryogenic Freezer duration decrease factor")
            public int durationDecreasePercentage = 75;

        }

        public static class LargePackager {
            @Config.Comment("The cost in percentage for a recipe's EU/t when run in the Large Packager.")
            @Config.RangeInt(min = 1)
            @Config.Name("Large Packager EU/t percentage cost")
            @Config.RequiresMcRestart
            public int euPercentage = 75;

            @Config.Comment("The amount of recipes processed at the same time per voltage tier difference.")
            @Config.RangeInt(min = 1)
            @Config.Name("Large Packager parallel recipes per voltage tier difference")
            @Config.RequiresMcRestart
            public int stack = 10;

            @Config.Comment("The duration percentage of a recipe when done in the Large Packager.")
            @Config.RangeInt(min = 1)
            @Config.Name("Large Packager duration decrease percentage")
            @Config.RequiresMcRestart
            public int durationPercentage = 90;

            @Config.Comment("The boost given to chanced outputs for a recipe when run in the Large Packager.")
            @Config.RangeInt(min = 1)
            @Config.Name("Large Packager chanced output boost percentage")
            @Config.RequiresMcRestart
            public int chancedBoostPercentage = 100;

            @Config.Comment("The casing material to use for the Large Packager.")
            @Config.Name("Large Packager casing material")
            @Config.RequiresMcRestart
            public String casingMaterial = "hg_1223";

        }

        public static class HeatingCoils {
            @Config.Comment("GregTech CE heating coils to blacklist from working in non-fusion multiblock machines. The default and recommended configs are for Superconducting and Fusion Coils. Example: \"superconductor\", \"fusion_coil\"")
            @Config.Name("GTCE Heating Coil Blacklist")
            @Config.RequiresMcRestart
            public String[] gtceHeatingCoilsBlacklist = new String[]{
                    "superconductor",
                    "fusion_coil"
            };
            @Config.Comment("Gregicality heating coils to blacklist from working in non-fusion multiblock machines.")
            @Config.Name("Gregicality Heating Coil Blacklist")
            @Config.RequiresMcRestart
            public String[] gregicalityheatingCoilsBlacklist = new String[]{
                    ""
            };
        }

        public static class ProcessingArray {
            @Config.Comment("Number of machines the Processing Array can use at a time. Default: 16")
            @Config.Name("Processing Array Machine Limit")
            @Config.RangeInt(min=1, max=64)
            public int processingArrayMachineLimit = 64;

            @Config.Comment({"Blacklist of machines for the Processing Array.",
                    "Add the unlocalized Recipe Map name to blacklist the machine."})
            @Config.Name("Processing Array Blacklist")
            public String[] machineBlackList = new String[]{
                    "mass_fab",
                    "replicator",
                    "circuit_assembler"
            };
        }

        public static class SteamMultis {
            @Config.Comment({"Steam to EU multiplier for steam multiblocks. 1.0 means 1 Steam -> 1EU. 2.0 means 1 Steam -> 2EU. 0.5 means 2 Steam -> 1EU"})
            @Config.RequiresMcRestart
            public double steamToEU = 0.5;

            @Config.Comment("Whether to use Steel casings instead of Bronze for the steam multiblocks.")
            @Config.RequiresMcRestart
            @Config.Name("Enable steel steam multis")
            public boolean useSteelMultis = false;
        }

        public static class CentralMonitor {
            @Config.Comment("The EU drain per tick for each screen.")
            @Config.Name("CentralMonitor cost")
            @Config.RangeInt(min = 0)
            public int euCost = 50;
        }

        public static class Prospector {
            @Config.Comment("The EU drain per tick that the Prospector interface is open. The values are for the MV, HV, LuV and ZPM prospectors.")
            @Config.RequiresMcRestart
            @Config.Name("Prospector scan cost")
            @Config.RangeInt(min = 0)
            public int[] scanCosts = {8, 32, 512, 2048};

            @Config.Comment("The radii in chunks the prospector will scan.")
            @Config.RequiresMcRestart
            @Config.Name("Prospector scan radii")
            @Config.RangeInt(min = 1)
            public int[] scanRadii = {2, 3, 6, 7};

            @Config.Comment("The ticks for the prospector to scan each chunk.")
            @Config.RequiresMcRestart
            @Config.Name("Prospector scan time cost")
            @Config.RangeInt(min = 1)
            public int[] scanTicks = {4, 3, 2, 1};

            @Config.Comment("The EU capacity of the prospectors.")
            @Config.RequiresMcRestart
            @Config.Name("Prospector energy capacity")
            @Config.RangeInt(min = 1000)
            public int[] energyCapacity = {12800, 51200, 3276800, 13107200};
        }

        public static class HyperReactors {
            @Config.Comment("The base EU/t production of the Hyper Reactor.")
            @Config.RequiresMcRestart
            @Config.Name("Hyper Reactor EU/t generation")
            @Config.RangeInt(min = 1)
            public int[] euGeneration = {GAValues.V[GAValues.UHV], GAValues.V[GAValues.UEV], GAValues.V[GAValues.UIV]};

            @Config.Comment("The fuel multiplier when the Reactor is boosted.")
            @Config.RequiresMcRestart
            @Config.Name("Hyper Reactor boosted fuel amount multiplier")
            @Config.RangeInt(min = 1)
            public int[] boostedFuelAmount = {2, 2, 2};

            @Config.Comment("The EU/t multiplier when the Reactor is boosted.")
            @Config.RequiresMcRestart
            @Config.Name("Hyper Reactor boosted EU/t multiplier")
            @Config.RangeInt(min = 1)
            public int[] boostedEuAmount = {3, 3, 3};

            @Config.Comment("The liquid that boosts the Reactor.")
            @Config.RequiresMcRestart
            @Config.Name("Hyper Reactor boosters")
            public String[] boosterFluid = {"plasma.helium", "plasma.radon", "degenerate_rhenium_plasma"};

            @Config.Comment("The amount of liquid that boosts the Reactor.")
            @Config.RequiresMcRestart
            @Config.Name("Hyper Reactor booster amount")
            public int[] boosterFluidAmounts = {10, 15, 2};
    }

        public static Extraction extraction;

        public static class Extraction {
            @Config.Comment({"List of reservoir types. Format: fluid_name, min_mb_fluid, max_mb_fluid, mb_per_tick_replenish, weight, [dim_blacklist], [dim_whitelist], [biome_dict_blacklist], [biome_dict_whitelist]"})
            @Config.RequiresMcRestart
            public static String[] reservoirs = new String[]{
                    "water, 5000000, 10000000, 10, 30, [], [0], [2,17,24,0,10], []",
                    "oil, 2500000, 15000000, 6, 40, [], [0], [], [2,17,24,0,10]",
                    "iodized_oil, 2500000, 15000000, 6, 40, [], [0], [], [2,17,24,0,10]",
                    "lava, 250000, 1000000, 1, 10, [1], [], [], []",
                    "sea_water, 50000000, 100000000, 100, 30, [], [0], [2,10,24], []"
            };

            @Config.Comment({"The chance that a chunk contains a fluid reservoir, default=0.5"})
            public static float reservoirChance = 0.5F;

            @Config.Name("Drilling mud per tick")
            @Config.Comment("The amount of drilling mud the Drilling Rig consumes every tick.")
            @Config.RangeInt(min = 0)
            @Config.RequiresMcRestart
            public static int drillingMud = 5;

            @Config.Comment({"This is the time scan coefficient, 100 mean 100% of the time, default=100"})
            @Config.RangeInt(min = 1, max = 1000)
            public static int timeToScanFactor = 100;
        }

        public static void addConfigReservoirs(String[] reservoirs) {
            for (int i = 0; i < reservoirs.length; i++) {
                String str = reservoirs[i];

                if (str.isEmpty()) continue;

                String fluid = null;
                int min = 0;
                int max = 0;
                int replenish = 0;
                int weight = 0;
                List<Integer> dimBlacklist = new ArrayList<>();
                List<Integer> dimWhitelist = new ArrayList<>();
                List<Integer> biomeBlacklist = new ArrayList<>();
                List<Integer> biomeWhitelist = new ArrayList<>();

                String remain = str;

                boolean inParens = false;
                int index = 0;

                while (remain.contains(",")) {
                    int endPos = remain.indexOf(",");

                    String current = remain.substring(0, endPos).trim();

                    if (index == 0) fluid = current;
                    else if (index == 1) {
                        try {
                            min = Integer.parseInt(current);
                            if (min < 0) {
                                throw new RuntimeException("Negative value for minimum mB fluid for reservoir " + current + " " + (i + 1));
                            }
                        } catch (NumberFormatException e) {
                            throw new RuntimeException("Invalid value for minimum mB fluid for reservoir  " + current + " " + (i + 1));
                        }
                    } else if (index == 2) {
                        try {
                            max = Integer.parseInt(current);
                            if (max < 0) {
                                throw new RuntimeException("Negative value for maximum mB fluid for reservoir " + (i + 1));
                            }
                        } catch (NumberFormatException e) {
                            throw new RuntimeException("Invalid value for maximum mB fluid for reservoir " + (i + 1));
                        }
                    } else if (index == 3) {
                        try {
                            replenish = Integer.parseInt(current);
                            if (replenish < 0) {
                                throw new RuntimeException("Negative value for mB replenished per tick for reservoir " + (i + 1));
                            }
                        } catch (NumberFormatException e) {
                            throw new RuntimeException("Invalid value for mB replenished per tick for reservoir " + (i + 1));
                        }
                    } else if (index == 4) {
                        try {
                            weight = Integer.parseInt(current);
                            if (weight < 0) {
                                throw new RuntimeException("Negative value for weight for reservoir " + (i + 1));
                            }
                        } catch (NumberFormatException e) {
                            throw new RuntimeException("Invalid value for weight for reservoir " + (i + 1));
                        }
                    } else if (index == 5) {
                        if (!inParens) {
                            current = current.substring(1);
                            inParens = true;
                        }

                        int cI = current.indexOf(",");
                        int bI = current.indexOf("]");

                        String value = current;
                        if (bI >= 0 && (cI == -1 || bI < cI)) {
                            value = value.substring(0, bI);
                            inParens = false;
                        }
                        if (value.length() > 0) {
                            try {
                                int dim = Integer.parseInt(value);
                                dimBlacklist.add(dim);
                            } catch (NumberFormatException e) {
                                throw new RuntimeException(value + "Invalid blacklist dimension for reservoir " + (i + 1));
                            }
                        }
                    } else if (index == 6) {
                        if (!inParens) {
                            current = current.substring(1);
                            inParens = true;
                        }

                        int cI = current.indexOf(",");
                        int bI = current.indexOf("]");

                        String value = current;
                        if (bI >= 0 && (cI == -1 || bI < cI)) {
                            value = value.substring(0, bI);
                            inParens = false;
                        }
                        if (value.length() > 0) {
                            try {
                                int dim = Integer.parseInt(value);
                                dimWhitelist.add(dim);
                            } catch (NumberFormatException e) {
                                throw new RuntimeException("Invalid whitelist dimension for reservoir " + (i + 1));
                            }
                        }
                    } else if (index == 7) {
                        if (!inParens) {
                            current = current.substring(1);
                            inParens = true;
                        }

                        int cI = current.indexOf(",");
                        int bI = current.indexOf("]");

                        String value = current;
                        if (bI >= 0 && (cI == -1 || bI < cI)) {
                            value = value.substring(0, bI);
                            inParens = false;
                        }
                        if (value.length() > 0) {
                            GALog.logger.info("biomeBlacklist value {}", value);
                            biomeBlacklist.add(Integer.valueOf(value.trim()));
                        }
                    } else if (index == 8) {
                        if (!inParens) {
                            current = current.substring(1);
                            inParens = true;
                        }

                        int cI = current.indexOf(",");
                        int bI = current.indexOf("]");

                        String value = current;
                        if (bI >= 0 && (cI == -1 || bI < cI)) {
                            value = value.substring(0, bI);
                            inParens = false;
                        }
                        if (value.length() > 0) {
                            GALog.logger.info("biomeWhitelist value {}", value);
                            biomeWhitelist.add(Integer.valueOf((value.trim())));
                        }
                    }

                    remain = remain.substring(endPos + 1);
                    if (!inParens) index++;
                }

                String current = remain.trim();

                if (!inParens) {
                    current = current.substring(1);
                }

                int cI = current.indexOf(",");
                int bI = current.indexOf("]");

                String value = current;
                if (cI == -1 || bI < cI) {
                    value = value.substring(0, bI);
                }
                if (value.length() > 0) {
                    GALog.logger.info("value {}", value);
                    biomeWhitelist.add(Integer.valueOf(value.trim()));
                }

                fluid = fluid.toLowerCase(Locale.ENGLISH);
                if (FluidRegistry.getFluid(fluid) == null) {
                    throw new RuntimeException("Invalid fluid name for reservoir " + (i + 1));
                }

                PumpjackHandler.ReservoirType res = PumpjackHandler.addReservoir(fluid, min, max, replenish, weight);
                res.dimensionWhitelist = dimWhitelist;
                res.dimensionBlacklist = dimBlacklist;
                res.biomeWhitelist = biomeWhitelist;
                res.biomeBlacklist = biomeBlacklist;

                GALog.logger.info("biomeWhitelist {}", biomeWhitelist);
                GALog.logger.info("biomeBlacklist {}", biomeBlacklist);

                GALog.logger.info("Added resevoir type " + fluid);
            }
        }
}
