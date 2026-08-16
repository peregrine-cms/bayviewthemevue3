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
    "Legends": {
      "type": "object",
      "x-type": "component",
      "properties": {
        "title": {
          "type": "string",
          "x-source": "inject",
          "x-form-label": "Title",
          "x-form-type": "text"
        },
        "text": {
          "type": "string",
          "x-source": "inject",
          "x-form-label": "Text",
          "x-form-type": "texteditor"
        },
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
        "swap": {
          "type": "string",
          "x-source": "inject",
          "x-form-label": "Swap Sides (mirror the two columns)",
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
  "name": "Legends",
  "componentPath": "bayviewthemevue3/components/legends",
  "package": "com.bayviewthemevue3.models",
  "modelName": "Legends",
  "classNameParent": "AbstractComponent"
}
//GEN]
*/

//GEN[:DEF
@Model(
        adaptables = Resource.class,
        resourceType = "bayviewthemevue3/components/legends",
        defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL,
        adapters = IComponent.class
)
@Exporter(
        name = "jackson",
        extensions = "json"
)

//GEN]
public class LegendsModel extends AbstractComponent {

    public LegendsModel(final Resource r) { super(r); }

    //GEN[:INJECT
    	/* {"type":"string","x-source":"inject","x-form-label":"Title","x-form-type":"text"} */
	@Inject
	private String title;

	/* {"type":"string","x-source":"inject","x-form-label":"Text","x-form-type":"texteditor"} */
	@Inject
	private String text;

	/* {"type":"string","x-source":"inject","x-form-label":"Image","x-form-type":"pathbrowser","x-form-browserRoot":"/content/bayviewthemevue3/assets"} */
	@Inject
	private String image;

	/* {"type":"string","x-source":"inject","x-form-label":"Image Alt Text","x-form-type":"text"} */
	@Inject
	private String imagealt;

	/* {"type":"string","x-source":"inject","x-form-label":"Swap Sides (mirror the two columns)","x-form-type":"materialswitch"} */
	@Inject
	private String swap;

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
    	/* {"type":"string","x-source":"inject","x-form-label":"Title","x-form-type":"text"} */
	public String getTitle() {
		return title;
	}

	/* {"type":"string","x-source":"inject","x-form-label":"Text","x-form-type":"texteditor"} */
	public String getText() {
		return text;
	}

	/* {"type":"string","x-source":"inject","x-form-label":"Image","x-form-type":"pathbrowser","x-form-browserRoot":"/content/bayviewthemevue3/assets"} */
	public String getImage() {
		return image;
	}

	/* {"type":"string","x-source":"inject","x-form-label":"Image Alt Text","x-form-type":"text"} */
	public String getImagealt() {
		return imagealt;
	}

	/* {"type":"string","x-source":"inject","x-form-label":"Swap Sides (mirror the two columns)","x-form-type":"materialswitch"} */
	public String getSwap() {
		return swap;
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
