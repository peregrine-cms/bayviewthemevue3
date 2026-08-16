package com.bayviewthemevue3.models;

import com.peregrine.nodetypes.models.AbstractComponent;
import com.peregrine.nodetypes.models.IComponent;
import com.peregrine.nodetypes.models.Container;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.Default;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.Exporter;
import org.apache.sling.models.annotations.Model;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;



/*
    //GEN[:DATA
    {
  "definitions": {
    "Band": {
      "type": "object",
      "x-type": "container",
      "properties": {
        "background": {
          "type": "string",
          "x-source": "inject",
          "x-form-label": "Background",
          "x-form-type": "materialselect",
          "x-default": "white",
          "properties": {
            "white": {
              "x-form-name": "White",
              "x-form-value": "white"
            },
            "cream": {
              "x-form-name": "Cream",
              "x-form-value": "cream"
            },
            "teal": {
              "x-form-name": "Teal",
              "x-form-value": "teal"
            }
          }
        },
        "contained": {
          "type": "string",
          "x-source": "inject",
          "x-form-label": "Constrain content width (page container)",
          "x-form-type": "materialswitch"
        },
        "flush": {
          "type": "string",
          "x-source": "inject",
          "x-form-label": "Flush (no vertical padding)",
          "x-form-type": "materialswitch"
        },
        "padding": {
          "type": "string",
          "x-source": "inject",
          "x-form-label": "Vertical Padding",
          "x-form-type": "materialselect",
          "x-default": "compact",
          "properties": {
            "compact": {
              "x-form-name": "Compact",
              "x-form-value": "compact"
            },
            "roomy": {
              "x-form-name": "Roomy (section)",
              "x-form-value": "roomy"
            }
          }
        },
        "visibility": {
          "type": "string",
          "x-source": "inject",
          "x-form-label": "Visibility",
          "x-form-type": "materialselect",
          "x-default": "all",
          "properties": {
            "all": {
              "x-form-name": "Always visible",
              "x-form-value": "all"
            },
            "desktop": {
              "x-form-name": "Desktop only (821px and up)",
              "x-form-value": "desktop"
            },
            "mobile": {
              "x-form-name": "Mobile only (up to 820px)",
              "x-form-value": "mobile"
            }
          }
        }
      }
    }
  },
  "name": "Band",
  "componentPath": "bayviewthemevue3/components/band",
  "package": "com.bayviewthemevue3.models",
  "modelName": "Band",
  "classNameParent": "Container"
}
//GEN]
*/

//GEN[:DEF
@Model(
        adaptables = Resource.class,
        resourceType = "bayviewthemevue3/components/band",
        defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL,
        adapters = IComponent.class
)
@Exporter(
        name = "jackson",
        extensions = "json"
)

//GEN]
public class BandModel extends Container {

    public BandModel(final Resource r) { super(r); }

    //GEN[:INJECT
    	/* {"type":"string","x-source":"inject","x-form-label":"Background","x-form-type":"materialselect","x-default":"white","properties":{"white":{"x-form-name":"White","x-form-value":"white"},"cream":{"x-form-name":"Cream","x-form-value":"cream"},"teal":{"x-form-name":"Teal","x-form-value":"teal"}}} */
	@Inject
	@Default(values ="white")
	private String background;

	/* {"type":"string","x-source":"inject","x-form-label":"Constrain content width (page container)","x-form-type":"materialswitch"} */
	@Inject
	private String contained;

	/* {"type":"string","x-source":"inject","x-form-label":"Flush (no vertical padding)","x-form-type":"materialswitch"} */
	@Inject
	private String flush;

	/* {"type":"string","x-source":"inject","x-form-label":"Vertical Padding","x-form-type":"materialselect","x-default":"compact","properties":{"compact":{"x-form-name":"Compact","x-form-value":"compact"},"roomy":{"x-form-name":"Roomy (section)","x-form-value":"roomy"}}} */
	@Inject
	@Default(values ="compact")
	private String padding;

	/* {"type":"string","x-source":"inject","x-form-label":"Visibility","x-form-type":"materialselect","x-default":"all","properties":{"all":{"x-form-name":"Always visible","x-form-value":"all"},"desktop":{"x-form-name":"Desktop only (821px and up)","x-form-value":"desktop"},"mobile":{"x-form-name":"Mobile only (up to 820px)","x-form-value":"mobile"}}} */
	@Inject
	@Default(values ="all")
	private String visibility;


//GEN]

    //GEN[:GETTERS
    	/* {"type":"string","x-source":"inject","x-form-label":"Background","x-form-type":"materialselect","x-default":"white","properties":{"white":{"x-form-name":"White","x-form-value":"white"},"cream":{"x-form-name":"Cream","x-form-value":"cream"},"teal":{"x-form-name":"Teal","x-form-value":"teal"}}} */
	public String getBackground() {
		return background;
	}

	/* {"type":"string","x-source":"inject","x-form-label":"Constrain content width (page container)","x-form-type":"materialswitch"} */
	public String getContained() {
		return contained;
	}

	/* {"type":"string","x-source":"inject","x-form-label":"Flush (no vertical padding)","x-form-type":"materialswitch"} */
	public String getFlush() {
		return flush;
	}

	/* {"type":"string","x-source":"inject","x-form-label":"Vertical Padding","x-form-type":"materialselect","x-default":"compact","properties":{"compact":{"x-form-name":"Compact","x-form-value":"compact"},"roomy":{"x-form-name":"Roomy (section)","x-form-value":"roomy"}}} */
	public String getPadding() {
		return padding;
	}

	/* {"type":"string","x-source":"inject","x-form-label":"Visibility","x-form-type":"materialselect","x-default":"all","properties":{"all":{"x-form-name":"Always visible","x-form-value":"all"},"desktop":{"x-form-name":"Desktop only (821px and up)","x-form-value":"desktop"},"mobile":{"x-form-name":"Mobile only (up to 820px)","x-form-value":"mobile"}}} */
	public String getVisibility() {
		return visibility;
	}


//GEN]

    //GEN[:CUSTOMGETTERS
    //GEN]

}
