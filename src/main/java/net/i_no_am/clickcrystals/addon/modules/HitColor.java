package net.i_no_am.clickcrystals.addon.modules;

import io.github.itzispyder.clickcrystals.modules.ModuleSetting;
import io.github.itzispyder.clickcrystals.modules.modules.DummyModule;
import io.github.itzispyder.clickcrystals.modules.settings.SettingSection;
import net.i_no_am.clickcrystals.addon.client.AddonCategory;

import java.awt.*;

public class HitColor extends DummyModule {
    public HitColor(){
    super("hit-color", AddonCategory.ADDON,"Change entity hit color.");
    }
    private final SettingSection scGeneral = getGeneralSection();
    public final ModuleSetting<Double> green = scGeneral.add(createDoubleSetting()
            .name("Green")
            .description("Decide how much green will be on the color pattern.")
            .def(255.0)
            .max(255.0)
            .min(0.0)
            .decimalPlaces(1)
            .build()
    );
    public final ModuleSetting<Double> blue = scGeneral.add(createDoubleSetting()
            .name("Blue")
            .description("Decide how much blue will be on the color pattern.")
            .def(255.0)
            .max(255.0)
            .min(0.0)
            .decimalPlaces(1)
            .build()
    );
    public final ModuleSetting<Double> red = scGeneral.add(createDoubleSetting()
            .name("Red")
            .description("Decide how much red will be on the color pattern.")
            .def(255.0)
            .max(255.0)
            .min(0.0)
            .decimalPlaces(1)
            .build()
    );
    public final ModuleSetting<Double> alpha = scGeneral.add(createDoubleSetting()
            .name("Alpha")
            .description("Decide the transparency level (0 is fully transparent, 255 is fully opaque).")
            .def(75.0)
            .max(255.0)
            .min(0.0)
            .decimalPlaces(1)
            .build()
    );

    public Color getColor() {
        return new Color(red.getVal().intValue(),green.getVal().intValue(),blue.getVal().intValue(),alpha.getVal().intValue());
    }
}
