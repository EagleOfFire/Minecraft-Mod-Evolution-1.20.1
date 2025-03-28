package ros.eagleoffire.rosevolution.client;

import net.minecraft.client.Minecraft;
import net.minecraft.client.player.LocalPlayer;
import ros.eagleoffire.rosevolution.screen.*;
import ros.eagleoffire.rosevolution.screen.specialisationScreen.*;
import ros.eagleoffire.rosevolution.screen.specialisationScreen.elementScreen.*;

public class ClientHooks {
    public static void openSpellSelectScreen(LocalPlayer player) {
        Minecraft.getInstance().setScreen(new SpellSelectScreen(player));
    }

    public static void openKatonJutsuScreen(LocalPlayer player) {
        Minecraft.getInstance().setScreen(new KatonJutsuScreen(player));
    }

    public static void openSpecialisationScreen(LocalPlayer player) {
        Minecraft.getInstance().setScreen(new SpecialisationScreen(player));
    }

    public static void openElementSelectScreen(LocalPlayer player) {
        Minecraft.getInstance().setScreen(new ElementSelectScreen(player));
    }

    public static void openMedicalScreen(LocalPlayer player, String rank){
        switch (rank) {
            case "D":
                Minecraft.getInstance().setScreen(new MedicalScreens.MedicalRangDScreen(player));
                break;
            case "C":
                Minecraft.getInstance().setScreen(new MedicalScreens.MedicalRangCScreen(player));
                break;
            case "B":
                Minecraft.getInstance().setScreen(new MedicalScreens.MedicalRangBScreen(player));
                break;
            case "A":
                Minecraft.getInstance().setScreen(new MedicalScreens.MedicalRangAScreen(player));
                break;
            case "S":
                Minecraft.getInstance().setScreen(new MedicalScreens.MedicalRangSScreen(player));
                break;
        }
    }

    public static void openGenjutsuScreen(LocalPlayer player, String rank){
        switch (rank) {
            case "D":
                Minecraft.getInstance().setScreen(new GenjutsuScreens.GenjutsuRangDScreen(player));
                break;
            case "C":
                Minecraft.getInstance().setScreen(new GenjutsuScreens.GenjutsuRangCScreen(player));
                break;
            case "B":
                Minecraft.getInstance().setScreen(new GenjutsuScreens.GenjutsuRangBScreen(player));
                break;
            case "A":
                Minecraft.getInstance().setScreen(new GenjutsuScreens.GenjutsuRangAScreen(player));
                break;
            case "S":
                Minecraft.getInstance().setScreen(new GenjutsuScreens.GenjutsuRangSScreen(player));
                break;
        }
    }

    public static void openKenjutsuScreen(LocalPlayer player, String rank){
        switch (rank) {
            case "D":
                Minecraft.getInstance().setScreen(new KenjutsuScreens.KenjutsuRangDScreen(player));
                break;
            case "C":
                Minecraft.getInstance().setScreen(new KenjutsuScreens.KenjutsuRangCScreen(player));
                break;
            case "B":
                Minecraft.getInstance().setScreen(new KenjutsuScreens.KenjutsuRangBScreen(player));
                break;
            case "A":
                Minecraft.getInstance().setScreen(new KenjutsuScreens.KenjutsuRangAScreen(player));
                break;
            case "S":
                Minecraft.getInstance().setScreen(new KenjutsuScreens.KenjutsuRangSScreen(player));
                break;
        }
    }

    public static void openTaijutsuScreen(LocalPlayer player, String rank){
        switch (rank) {
            case "D":
                Minecraft.getInstance().setScreen(new TaijutsuScreens.TaijutsuRangDScreen(player));
                break;
            case "C":
                Minecraft.getInstance().setScreen(new TaijutsuScreens.TaijutsuRangCScreen(player));
                break;
            case "B":
                Minecraft.getInstance().setScreen(new TaijutsuScreens.TaijutsuRangBScreen(player));
                break;
            case "A":
                Minecraft.getInstance().setScreen(new TaijutsuScreens.TaijutsuRangAScreen(player));
                break;
            case "S":
                Minecraft.getInstance().setScreen(new TaijutsuScreens.TaijutsuRangSScreen(player));
                break;
        }
    }

    public static void openClanSpecialisationScreen(LocalPlayer player, String rank){
        switch (rank) {
            case "D":
                Minecraft.getInstance().setScreen(new TaijutsuScreens.TaijutsuRangDScreen(player));
                break;
            case "C":
                Minecraft.getInstance().setScreen(new TaijutsuScreens.TaijutsuRangCScreen(player));
                break;
            case "B":
                Minecraft.getInstance().setScreen(new TaijutsuScreens.TaijutsuRangBScreen(player));
                break;
            case "A":
                Minecraft.getInstance().setScreen(new TaijutsuScreens.TaijutsuRangAScreen(player));
                break;
            case "S":
                Minecraft.getInstance().setScreen(new TaijutsuScreens.TaijutsuRangSScreen(player));
                break;
        }
    }

    public static void openElementRankScreen(LocalPlayer player, String element, String rank) {
        switch (element) {
            case "Doton":
                switch (rank) {
                    case "D":
                        Minecraft.getInstance().setScreen(new DotonScreens.DotonRangDScreen(player));
                        break;
                    case "C":
                        Minecraft.getInstance().setScreen(new DotonScreens.DotonRangCScreen(player));
                        break;
                    case "B":
                        Minecraft.getInstance().setScreen(new DotonScreens.DotonRangBScreen(player));
                        break;
                    case "A":
                        Minecraft.getInstance().setScreen(new DotonScreens.DotonRangAScreen(player));
                        break;
                    case "S":
                        Minecraft.getInstance().setScreen(new DotonScreens.DotonRangSScreen(player));
                        break;
                }
                break;
            case "Futon":
                switch (rank) {
                    case "D":
                        Minecraft.getInstance().setScreen(new FutonScreens.FutonRangDScreen(player));
                        break;
                    case "C":
                        Minecraft.getInstance().setScreen(new FutonScreens.FutonRangCScreen(player));
                        break;
                    case "B":
                        Minecraft.getInstance().setScreen(new FutonScreens.FutonRangBScreen(player));
                        break;
                    case "A":
                        Minecraft.getInstance().setScreen(new FutonScreens.FutonRangAScreen(player));
                        break;
                    case "S":
                        Minecraft.getInstance().setScreen(new FutonScreens.FutonRangSScreen(player));
                        break;
                }
                break;
            case "Katon":
                switch (rank) {
                    case "D":
                        Minecraft.getInstance().setScreen(new KatonScreens.KatonRangDScreen(player));
                        break;
                    case "C":
                        Minecraft.getInstance().setScreen(new KatonScreens.KatonRangCScreen(player));
                        break;
                    case "B":
                        Minecraft.getInstance().setScreen(new KatonScreens.KatonRangBScreen(player));
                        break;
                    case "A":
                        Minecraft.getInstance().setScreen(new KatonScreens.KatonRangAScreen(player));
                        break;
                    case "S":
                        Minecraft.getInstance().setScreen(new KatonScreens.KatonRangSScreen(player));
                        break;
                }
                break;
            case "Raiton":
                switch (rank) {
                    case "D":
                        Minecraft.getInstance().setScreen(new RaitonScreens.RaitonRangDScreen(player));
                        break;
                    case "C":
                        Minecraft.getInstance().setScreen(new RaitonScreens.RaitonRangCScreen(player));
                        break;
                    case "B":
                        Minecraft.getInstance().setScreen(new RaitonScreens.RaitonRangBScreen(player));
                        break;
                    case "A":
                        Minecraft.getInstance().setScreen(new RaitonScreens.RaitonRangAScreen(player));
                        break;
                    case "S":
                        Minecraft.getInstance().setScreen(new RaitonScreens.RaitonRangSScreen(player));
                        break;
                }
                break;
            case "Suiton":
                switch (rank) {
                    case "D":
                        Minecraft.getInstance().setScreen(new SuitonScreens.SuitonRangDScreen(player));
                        break;
                    case "C":
                        Minecraft.getInstance().setScreen(new SuitonScreens.SuitonRangCScreen(player));
                        break;
                    case "B":
                        Minecraft.getInstance().setScreen(new SuitonScreens.SuitonRangBScreen(player));
                        break;
                    case "A":
                        Minecraft.getInstance().setScreen(new SuitonScreens.SuitonRangAScreen(player));
                        break;
                    case "S":
                        Minecraft.getInstance().setScreen(new SuitonScreens.SuitonRangSScreen(player));
                        break;
                }
                break;
            default:
                System.out.println("Unknown element: " + element);
        }
    }


}
