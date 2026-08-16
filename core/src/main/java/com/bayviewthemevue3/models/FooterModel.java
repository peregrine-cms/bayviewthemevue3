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
    "Footer": {
      "type": "object",
      "x-type": "component",
      "properties": {
        "brand": {
          "type": "string",
          "x-source": "inject",
          "x-form-label": "Brand (words stack per line)",
          "x-form-type": "text"
        },
        "address": {
          "type": "string",
          "x-source": "inject",
          "x-form-label": "Address",
          "x-form-type": "texteditor"
        },
        "tel": {
          "type": "string",
          "x-source": "inject",
          "x-form-label": "Telephone",
          "x-form-type": "text"
        },
        "email": {
          "type": "string",
          "x-source": "inject",
          "x-form-label": "E-Mail",
          "x-form-type": "text"
        },
        "social": {
          "type": "string",
          "x-source": "inject",
          "x-form-label": "Social Handle",
          "x-form-type": "text"
        },
        "copyright": {
          "type": "string",
          "x-source": "inject",
          "x-form-label": "Copyright Line",
          "x-form-type": "text"
        },
        "image": {
          "type": "string",
          "x-source": "inject",
          "x-form-label": "Photo (right)",
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
        },
        "legallinks": {
          "type": "object",
          "x-source": "inject",
          "x-form-label": "Tiles",
          "x-form-type": "collection",
          "x-form-fieldLabel": [
            "name"
          ],
          "properties": {
            "name": {
              "type": "string",
              "x-source": "inject",
              "x-form-label": "Name",
              "x-form-type": "text"
            },
            "link": {
              "type": "string",
              "x-source": "inject",
              "x-form-label": "Link",
              "x-form-type": "text"
            }
          }
        }
      }
    }
  },
  "name": "Footer",
  "componentPath": "bayviewthemevue3/components/footer",
  "package": "com.bayviewthemevue3.models",
  "modelName": "Footer",
  "classNameParent": "AbstractComponent"
}
//GEN]
*/

//GEN[:DEF
@Model(
        adaptables = Resource.class,
        resourceType = "bayviewthemevue3/components/footer",
        defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL,
        adapters = IComponent.class
)
@Exporter(
        name = "jackson",
        extensions = "json"
)

//GEN]
public class FooterModel extends AbstractComponent {

    public FooterModel(final Resource r) { super(r); }

    //GEN[:INJECT
    	/* {"type":"string","x-source":"inject","x-form-label":"Brand (words stack per line)","x-form-type":"text"} */
	@Inject
	private String brand;

	/* {"type":"string","x-source":"inject","x-form-label":"Address","x-form-type":"texteditor"} */
	@Inject
	private String address;

	/* {"type":"string","x-source":"inject","x-form-label":"Telephone","x-form-type":"text"} */
	@Inject
	private String tel;

	/* {"type":"string","x-source":"inject","x-form-label":"E-Mail","x-form-type":"text"} */
	@Inject
	private String email;

	/* {"type":"string","x-source":"inject","x-form-label":"Social Handle","x-form-type":"text"} */
	@Inject
	private String social;

	/* {"type":"string","x-source":"inject","x-form-label":"Copyright Line","x-form-type":"text"} */
	@Inject
	private String copyright;

	/* {"type":"string","x-source":"inject","x-form-label":"Photo (right)","x-form-type":"pathbrowser","x-form-browserRoot":"/content/bayviewthemevue3/assets"} */
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

	/* {"type":"object","x-source":"inject","x-form-label":"Tiles","x-form-type":"collection","x-form-fieldLabel":["name"],"properties":{"name":{"type":"string","x-source":"inject","x-form-label":"Name","x-form-type":"text"},"link":{"type":"string","x-source":"inject","x-form-label":"Link","x-form-type":"text"}}} */
	@Inject
	private List<IComponent> legallinks;


//GEN]

    //GEN[:GETTERS
    	/* {"type":"string","x-source":"inject","x-form-label":"Brand (words stack per line)","x-form-type":"text"} */
	public String getBrand() {
		return brand;
	}

	/* {"type":"string","x-source":"inject","x-form-label":"Address","x-form-type":"texteditor"} */
	public String getAddress() {
		return address;
	}

	/* {"type":"string","x-source":"inject","x-form-label":"Telephone","x-form-type":"text"} */
	public String getTel() {
		return tel;
	}

	/* {"type":"string","x-source":"inject","x-form-label":"E-Mail","x-form-type":"text"} */
	public String getEmail() {
		return email;
	}

	/* {"type":"string","x-source":"inject","x-form-label":"Social Handle","x-form-type":"text"} */
	public String getSocial() {
		return social;
	}

	/* {"type":"string","x-source":"inject","x-form-label":"Copyright Line","x-form-type":"text"} */
	public String getCopyright() {
		return copyright;
	}

	/* {"type":"string","x-source":"inject","x-form-label":"Photo (right)","x-form-type":"pathbrowser","x-form-browserRoot":"/content/bayviewthemevue3/assets"} */
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

	/* {"type":"object","x-source":"inject","x-form-label":"Tiles","x-form-type":"collection","x-form-fieldLabel":["name"],"properties":{"name":{"type":"string","x-source":"inject","x-form-label":"Name","x-form-type":"text"},"link":{"type":"string","x-source":"inject","x-form-label":"Link","x-form-type":"text"}}} */
	public List<IComponent> getLegallinks() {
		return legallinks;
	}


//GEN]

    //GEN[:CUSTOMGETTERS
    //GEN]

}
