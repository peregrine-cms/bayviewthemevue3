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
    "Blkimage": {
      "type": "object",
      "x-type": "component",
      "properties": {
        "image": {
          "type": "string",
          "x-source": "inject",
          "x-form-label": "Image",
          "x-form-type": "pathbrowser",
          "x-form-browserRoot": "/content/bayviewthemevue3/assets"
        },
        "imagealt": {
          "type": "string",
          "x-source": "inject",
          "x-form-label": "Image Alt Text",
          "x-form-type": "text"
        },
        "height": {
          "type": "string",
          "x-source": "inject",
          "x-form-label": "Height",
          "x-form-type": "materialselect",
          "x-default": "medium",
          "properties": {
            "auto": {
              "x-form-name": "Auto (natural)",
              "x-form-value": "auto"
            },
            "small": {
              "x-form-name": "Small (220px cover)",
              "x-form-value": "small"
            },
            "medium": {
              "x-form-name": "Medium (340px cover)",
              "x-form-value": "medium"
            },
            "large": {
              "x-form-name": "Large (480px cover)",
              "x-form-value": "large"
            },
            "fill": {
              "x-form-name": "Fill parent",
              "x-form-value": "fill"
            },
            "banner": {
              "x-form-name": "Banner (420px cover)",
              "x-form-value": "banner"
            },
            "tile": {
              "x-form-name": "Tile (265px cover)",
              "x-form-value": "tile"
            }
          }
        },
        "fullbleed": {
          "type": "string",
          "x-source": "inject",
          "x-form-label": "Full bleed (break out of the container width)",
          "x-form-type": "materialswitch"
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
        },
        "focusx": {
          "type": "string",
          "x-source": "inject",
          "x-form-label": "Focus point X (0 = left, 100 = right)",
          "x-form-type": "materialrange",
          "x-form-min": 0,
          "x-form-max": 100,
          "x-default": 50
        },
        "focusy": {
          "type": "string",
          "x-source": "inject",
          "x-form-label": "Focus point Y (0 = top, 100 = bottom)",
          "x-form-type": "materialrange",
          "x-form-min": 0,
          "x-form-max": 100,
          "x-default": 50
        },
        "zoom": {
          "type": "string",
          "x-source": "inject",
          "x-form-label": "Zoom (100 = normal, up to 200%)",
          "x-form-type": "materialrange",
          "x-form-min": 100,
          "x-form-max": 200,
          "x-default": 100
        },
        "videourl": {
          "type": "string",
          "x-source": "inject",
          "x-form-label": "Vimeo video (URL or ID) — uses the image as its poster",
          "x-form-type": "text"
        },
        "videoautoplay": {
          "type": "string",
          "x-source": "inject",
          "x-form-label": "Autoplay video (muted, looped)",
          "x-form-type": "materialswitch"
        }
      }
    }
  },
  "name": "Blkimage",
  "componentPath": "bayviewthemevue3/components/blkimage",
  "package": "com.bayviewthemevue3.models",
  "modelName": "Blkimage",
  "classNameParent": "AbstractComponent"
}
//GEN]
*/

//GEN[:DEF
@Model(
        adaptables = Resource.class,
        resourceType = "bayviewthemevue3/components/blkimage",
        defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL,
        adapters = IComponent.class
)
@Exporter(
        name = "jackson",
        extensions = "json"
)

//GEN]
public class BlkimageModel extends AbstractComponent {

    public BlkimageModel(final Resource r) { super(r); }

    //GEN[:INJECT
    	/* {"type":"string","x-source":"inject","x-form-label":"Image","x-form-type":"pathbrowser","x-form-browserRoot":"/content/bayviewthemevue3/assets"} */
	@Inject
	private String image;

	/* {"type":"string","x-source":"inject","x-form-label":"Image Alt Text","x-form-type":"text"} */
	@Inject
	private String imagealt;

	/* {"type":"string","x-source":"inject","x-form-label":"Height","x-form-type":"materialselect","x-default":"medium","properties":{"auto":{"x-form-name":"Auto (natural)","x-form-value":"auto"},"small":{"x-form-name":"Small (220px cover)","x-form-value":"small"},"medium":{"x-form-name":"Medium (340px cover)","x-form-value":"medium"},"large":{"x-form-name":"Large (480px cover)","x-form-value":"large"},"fill":{"x-form-name":"Fill parent","x-form-value":"fill"},"banner":{"x-form-name":"Banner (420px cover)","x-form-value":"banner"},"tile":{"x-form-name":"Tile (265px cover)","x-form-value":"tile"}}} */
	@Inject
	@Default(values ="medium")
	private String height;

	/* {"type":"string","x-source":"inject","x-form-label":"Full bleed (break out of the container width)","x-form-type":"materialswitch"} */
	@Inject
	private String fullbleed;

	/* {"type":"string","x-source":"inject","x-form-label":"Visibility","x-form-type":"materialselect","x-default":"all","properties":{"all":{"x-form-name":"Always visible","x-form-value":"all"},"desktop":{"x-form-name":"Desktop only (821px and up)","x-form-value":"desktop"},"mobile":{"x-form-name":"Mobile only (up to 820px)","x-form-value":"mobile"}}} */
	@Inject
	@Default(values ="all")
	private String visibility;

	/* {"type":"string","x-source":"inject","x-form-label":"Focus point X (0 = left, 100 = right)","x-form-type":"materialrange","x-form-min":0,"x-form-max":100,"x-default":50} */
	@Inject
	@Default(values ="50")
	private String focusx;

	/* {"type":"string","x-source":"inject","x-form-label":"Focus point Y (0 = top, 100 = bottom)","x-form-type":"materialrange","x-form-min":0,"x-form-max":100,"x-default":50} */
	@Inject
	@Default(values ="50")
	private String focusy;

	/* {"type":"string","x-source":"inject","x-form-label":"Zoom (100 = normal, up to 200%)","x-form-type":"materialrange","x-form-min":100,"x-form-max":200,"x-default":100} */
	@Inject
	@Default(values ="100")
	private String zoom;

	/* {"type":"string","x-source":"inject","x-form-label":"Vimeo video (URL or ID) — uses the image as its poster","x-form-type":"text"} */
	@Inject
	private String videourl;

	/* {"type":"string","x-source":"inject","x-form-label":"Autoplay video (muted, looped)","x-form-type":"materialswitch"} */
	@Inject
	private String videoautoplay;


//GEN]

    //GEN[:GETTERS
    	/* {"type":"string","x-source":"inject","x-form-label":"Image","x-form-type":"pathbrowser","x-form-browserRoot":"/content/bayviewthemevue3/assets"} */
	public String getImage() {
		return image;
	}

	/* {"type":"string","x-source":"inject","x-form-label":"Image Alt Text","x-form-type":"text"} */
	public String getImagealt() {
		return imagealt;
	}

	/* {"type":"string","x-source":"inject","x-form-label":"Height","x-form-type":"materialselect","x-default":"medium","properties":{"auto":{"x-form-name":"Auto (natural)","x-form-value":"auto"},"small":{"x-form-name":"Small (220px cover)","x-form-value":"small"},"medium":{"x-form-name":"Medium (340px cover)","x-form-value":"medium"},"large":{"x-form-name":"Large (480px cover)","x-form-value":"large"},"fill":{"x-form-name":"Fill parent","x-form-value":"fill"},"banner":{"x-form-name":"Banner (420px cover)","x-form-value":"banner"},"tile":{"x-form-name":"Tile (265px cover)","x-form-value":"tile"}}} */
	public String getHeight() {
		return height;
	}

	/* {"type":"string","x-source":"inject","x-form-label":"Full bleed (break out of the container width)","x-form-type":"materialswitch"} */
	public String getFullbleed() {
		return fullbleed;
	}

	/* {"type":"string","x-source":"inject","x-form-label":"Visibility","x-form-type":"materialselect","x-default":"all","properties":{"all":{"x-form-name":"Always visible","x-form-value":"all"},"desktop":{"x-form-name":"Desktop only (821px and up)","x-form-value":"desktop"},"mobile":{"x-form-name":"Mobile only (up to 820px)","x-form-value":"mobile"}}} */
	public String getVisibility() {
		return visibility;
	}

	/* {"type":"string","x-source":"inject","x-form-label":"Focus point X (0 = left, 100 = right)","x-form-type":"materialrange","x-form-min":0,"x-form-max":100,"x-default":50} */
	public String getFocusx() {
		return focusx;
	}

	/* {"type":"string","x-source":"inject","x-form-label":"Focus point Y (0 = top, 100 = bottom)","x-form-type":"materialrange","x-form-min":0,"x-form-max":100,"x-default":50} */
	public String getFocusy() {
		return focusy;
	}

	/* {"type":"string","x-source":"inject","x-form-label":"Zoom (100 = normal, up to 200%)","x-form-type":"materialrange","x-form-min":100,"x-form-max":200,"x-default":100} */
	public String getZoom() {
		return zoom;
	}

	/* {"type":"string","x-source":"inject","x-form-label":"Vimeo video (URL or ID) — uses the image as its poster","x-form-type":"text"} */
	public String getVideourl() {
		return videourl;
	}

	/* {"type":"string","x-source":"inject","x-form-label":"Autoplay video (muted, looped)","x-form-type":"materialswitch"} */
	public String getVideoautoplay() {
		return videoautoplay;
	}


//GEN]

    //GEN[:CUSTOMGETTERS
    //GEN]

}
