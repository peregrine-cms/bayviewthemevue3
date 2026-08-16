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
    "Splitrow": {
      "type": "object",
      "x-type": "container",
      "properties": {
        "ratio": {
          "type": "string",
          "x-source": "inject",
          "x-form-label": "Column Ratio (left-right)",
          "x-form-type": "materialselect",
          "x-default": "50-50",
          "properties": {
            "50-50": {
              "x-form-name": "50 / 50",
              "x-form-value": "50-50"
            },
            "58-42": {
              "x-form-name": "58 / 42",
              "x-form-value": "58-42"
            },
            "42-58": {
              "x-form-name": "42 / 58",
              "x-form-value": "42-58"
            },
            "62-38": {
              "x-form-name": "62 / 38",
              "x-form-value": "62-38"
            },
            "38-62": {
              "x-form-name": "38 / 62",
              "x-form-value": "38-62"
            }
          }
        },
        "leftbg": {
          "type": "string",
          "x-source": "inject",
          "x-form-label": "Left Background",
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
            },
            "none": {
              "x-form-name": "None (transparent)",
              "x-form-value": "none"
            }
          }
        },
        "rightbg": {
          "type": "string",
          "x-source": "inject",
          "x-form-label": "Right Background",
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
            },
            "none": {
              "x-form-name": "None (transparent)",
              "x-form-value": "none"
            }
          }
        },
        "swap": {
          "type": "string",
          "x-source": "inject",
          "x-form-label": "Swap Sides (mirror the two columns)",
          "x-form-type": "materialswitch"
        },
        "minheight": {
          "type": "string",
          "x-source": "inject",
          "x-form-label": "Minimum Height",
          "x-form-type": "materialselect",
          "x-default": "auto",
          "properties": {
            "auto": {
              "x-form-name": "Auto",
              "x-form-value": "auto"
            },
            "medium": {
              "x-form-name": "Medium (420px)",
              "x-form-value": "medium"
            },
            "tall": {
              "x-form-name": "Tall (540px)",
              "x-form-value": "tall"
            },
            "large": {
              "x-form-name": "Large (520px)",
              "x-form-value": "large"
            }
          }
        },
        "leftflush": {
          "type": "string",
          "x-source": "inject",
          "x-form-label": "Left column flush (no padding, media edge-to-edge)",
          "x-form-type": "materialswitch"
        },
        "rightflush": {
          "type": "string",
          "x-source": "inject",
          "x-form-label": "Right column flush (no padding, media edge-to-edge)",
          "x-form-type": "materialswitch"
        },
        "valign": {
          "type": "string",
          "x-source": "inject",
          "x-form-label": "Vertical Alignment",
          "x-form-type": "materialselect",
          "x-default": "center",
          "properties": {
            "center": {
              "x-form-name": "Center",
              "x-form-value": "center"
            },
            "top": {
              "x-form-name": "Top",
              "x-form-value": "top"
            },
            "spread": {
              "x-form-name": "Spread (first top, last bottom)",
              "x-form-value": "spread"
            }
          }
        },
        "gap": {
          "type": "string",
          "x-source": "inject",
          "x-form-label": "Gap between columns",
          "x-form-type": "materialselect",
          "x-default": "none",
          "properties": {
            "none": {
              "x-form-name": "None",
              "x-form-value": "none"
            },
            "small": {
              "x-form-name": "Small (14px)",
              "x-form-value": "small"
            },
            "normal": {
              "x-form-name": "Normal (24-48px)",
              "x-form-value": "normal"
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
  "name": "Splitrow",
  "componentPath": "bayviewthemevue3/components/splitrow",
  "package": "com.bayviewthemevue3.models",
  "modelName": "Splitrow",
  "classNameParent": "Container"
}
//GEN]
*/

//GEN[:DEF
@Model(
        adaptables = Resource.class,
        resourceType = "bayviewthemevue3/components/splitrow",
        defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL,
        adapters = IComponent.class
)
@Exporter(
        name = "jackson",
        extensions = "json"
)

//GEN]
public class SplitrowModel extends Container {

    public SplitrowModel(final Resource r) { super(r); }

    //GEN[:INJECT
    	/* {"type":"string","x-source":"inject","x-form-label":"Column Ratio (left-right)","x-form-type":"materialselect","x-default":"50-50","properties":{"50-50":{"x-form-name":"50 / 50","x-form-value":"50-50"},"58-42":{"x-form-name":"58 / 42","x-form-value":"58-42"},"42-58":{"x-form-name":"42 / 58","x-form-value":"42-58"},"62-38":{"x-form-name":"62 / 38","x-form-value":"62-38"},"38-62":{"x-form-name":"38 / 62","x-form-value":"38-62"}}} */
	@Inject
	@Default(values ="50-50")
	private String ratio;

	/* {"type":"string","x-source":"inject","x-form-label":"Left Background","x-form-type":"materialselect","x-default":"white","properties":{"white":{"x-form-name":"White","x-form-value":"white"},"cream":{"x-form-name":"Cream","x-form-value":"cream"},"teal":{"x-form-name":"Teal","x-form-value":"teal"},"none":{"x-form-name":"None (transparent)","x-form-value":"none"}}} */
	@Inject
	@Default(values ="white")
	private String leftbg;

	/* {"type":"string","x-source":"inject","x-form-label":"Right Background","x-form-type":"materialselect","x-default":"white","properties":{"white":{"x-form-name":"White","x-form-value":"white"},"cream":{"x-form-name":"Cream","x-form-value":"cream"},"teal":{"x-form-name":"Teal","x-form-value":"teal"},"none":{"x-form-name":"None (transparent)","x-form-value":"none"}}} */
	@Inject
	@Default(values ="white")
	private String rightbg;

	/* {"type":"string","x-source":"inject","x-form-label":"Swap Sides (mirror the two columns)","x-form-type":"materialswitch"} */
	@Inject
	private String swap;

	/* {"type":"string","x-source":"inject","x-form-label":"Minimum Height","x-form-type":"materialselect","x-default":"auto","properties":{"auto":{"x-form-name":"Auto","x-form-value":"auto"},"medium":{"x-form-name":"Medium (420px)","x-form-value":"medium"},"tall":{"x-form-name":"Tall (540px)","x-form-value":"tall"},"large":{"x-form-name":"Large (520px)","x-form-value":"large"}}} */
	@Inject
	@Default(values ="auto")
	private String minheight;

	/* {"type":"string","x-source":"inject","x-form-label":"Left column flush (no padding, media edge-to-edge)","x-form-type":"materialswitch"} */
	@Inject
	private String leftflush;

	/* {"type":"string","x-source":"inject","x-form-label":"Right column flush (no padding, media edge-to-edge)","x-form-type":"materialswitch"} */
	@Inject
	private String rightflush;

	/* {"type":"string","x-source":"inject","x-form-label":"Vertical Alignment","x-form-type":"materialselect","x-default":"center","properties":{"center":{"x-form-name":"Center","x-form-value":"center"},"top":{"x-form-name":"Top","x-form-value":"top"},"spread":{"x-form-name":"Spread (first top, last bottom)","x-form-value":"spread"}}} */
	@Inject
	@Default(values ="center")
	private String valign;

	/* {"type":"string","x-source":"inject","x-form-label":"Gap between columns","x-form-type":"materialselect","x-default":"none","properties":{"none":{"x-form-name":"None","x-form-value":"none"},"small":{"x-form-name":"Small (14px)","x-form-value":"small"},"normal":{"x-form-name":"Normal (24-48px)","x-form-value":"normal"}}} */
	@Inject
	@Default(values ="none")
	private String gap;

	/* {"type":"string","x-source":"inject","x-form-label":"Visibility","x-form-type":"materialselect","x-default":"all","properties":{"all":{"x-form-name":"Always visible","x-form-value":"all"},"desktop":{"x-form-name":"Desktop only (821px and up)","x-form-value":"desktop"},"mobile":{"x-form-name":"Mobile only (up to 820px)","x-form-value":"mobile"}}} */
	@Inject
	@Default(values ="all")
	private String visibility;


//GEN]

    //GEN[:GETTERS
    	/* {"type":"string","x-source":"inject","x-form-label":"Column Ratio (left-right)","x-form-type":"materialselect","x-default":"50-50","properties":{"50-50":{"x-form-name":"50 / 50","x-form-value":"50-50"},"58-42":{"x-form-name":"58 / 42","x-form-value":"58-42"},"42-58":{"x-form-name":"42 / 58","x-form-value":"42-58"},"62-38":{"x-form-name":"62 / 38","x-form-value":"62-38"},"38-62":{"x-form-name":"38 / 62","x-form-value":"38-62"}}} */
	public String getRatio() {
		return ratio;
	}

	/* {"type":"string","x-source":"inject","x-form-label":"Left Background","x-form-type":"materialselect","x-default":"white","properties":{"white":{"x-form-name":"White","x-form-value":"white"},"cream":{"x-form-name":"Cream","x-form-value":"cream"},"teal":{"x-form-name":"Teal","x-form-value":"teal"},"none":{"x-form-name":"None (transparent)","x-form-value":"none"}}} */
	public String getLeftbg() {
		return leftbg;
	}

	/* {"type":"string","x-source":"inject","x-form-label":"Right Background","x-form-type":"materialselect","x-default":"white","properties":{"white":{"x-form-name":"White","x-form-value":"white"},"cream":{"x-form-name":"Cream","x-form-value":"cream"},"teal":{"x-form-name":"Teal","x-form-value":"teal"},"none":{"x-form-name":"None (transparent)","x-form-value":"none"}}} */
	public String getRightbg() {
		return rightbg;
	}

	/* {"type":"string","x-source":"inject","x-form-label":"Swap Sides (mirror the two columns)","x-form-type":"materialswitch"} */
	public String getSwap() {
		return swap;
	}

	/* {"type":"string","x-source":"inject","x-form-label":"Minimum Height","x-form-type":"materialselect","x-default":"auto","properties":{"auto":{"x-form-name":"Auto","x-form-value":"auto"},"medium":{"x-form-name":"Medium (420px)","x-form-value":"medium"},"tall":{"x-form-name":"Tall (540px)","x-form-value":"tall"},"large":{"x-form-name":"Large (520px)","x-form-value":"large"}}} */
	public String getMinheight() {
		return minheight;
	}

	/* {"type":"string","x-source":"inject","x-form-label":"Left column flush (no padding, media edge-to-edge)","x-form-type":"materialswitch"} */
	public String getLeftflush() {
		return leftflush;
	}

	/* {"type":"string","x-source":"inject","x-form-label":"Right column flush (no padding, media edge-to-edge)","x-form-type":"materialswitch"} */
	public String getRightflush() {
		return rightflush;
	}

	/* {"type":"string","x-source":"inject","x-form-label":"Vertical Alignment","x-form-type":"materialselect","x-default":"center","properties":{"center":{"x-form-name":"Center","x-form-value":"center"},"top":{"x-form-name":"Top","x-form-value":"top"},"spread":{"x-form-name":"Spread (first top, last bottom)","x-form-value":"spread"}}} */
	public String getValign() {
		return valign;
	}

	/* {"type":"string","x-source":"inject","x-form-label":"Gap between columns","x-form-type":"materialselect","x-default":"none","properties":{"none":{"x-form-name":"None","x-form-value":"none"},"small":{"x-form-name":"Small (14px)","x-form-value":"small"},"normal":{"x-form-name":"Normal (24-48px)","x-form-value":"normal"}}} */
	public String getGap() {
		return gap;
	}

	/* {"type":"string","x-source":"inject","x-form-label":"Visibility","x-form-type":"materialselect","x-default":"all","properties":{"all":{"x-form-name":"Always visible","x-form-value":"all"},"desktop":{"x-form-name":"Desktop only (821px and up)","x-form-value":"desktop"},"mobile":{"x-form-name":"Mobile only (up to 820px)","x-form-value":"mobile"}}} */
	public String getVisibility() {
		return visibility;
	}


//GEN]

    //GEN[:CUSTOMGETTERS
    //GEN]

}
