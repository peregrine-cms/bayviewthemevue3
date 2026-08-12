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
    "Contact": {
      "type": "object",
      "x-type": "component",
      "properties": {
        "title": {
          "type": "string",
          "x-source": "inject",
          "x-form-label": "Heading",
          "x-form-type": "text"
        },
        "image": {
          "type": "string",
          "x-source": "inject",
          "x-form-label": "Side Photo",
          "x-form-type": "pathbrowser",
          "x-form-browserRoot": "/content/bayviewthemevue3/assets"
        },
        "imagealt": {
          "type": "string",
          "x-source": "inject",
          "x-form-label": "Image Alt Text",
          "x-form-type": "text"
        },
        "namelabel": {
          "type": "string",
          "x-source": "inject",
          "x-form-label": "Name Field Label",
          "x-form-type": "text"
        },
        "emaillabel": {
          "type": "string",
          "x-source": "inject",
          "x-form-label": "Email Field Label",
          "x-form-type": "text"
        },
        "messagelabel": {
          "type": "string",
          "x-source": "inject",
          "x-form-label": "Message Field Label",
          "x-form-type": "text"
        },
        "buttontext": {
          "type": "string",
          "x-source": "inject",
          "x-form-label": "Button Label",
          "x-form-type": "text"
        },
        "sendingtext": {
          "type": "string",
          "x-source": "inject",
          "x-form-label": "Button Label While Sending",
          "x-form-type": "text"
        },
        "successtext": {
          "type": "string",
          "x-source": "inject",
          "x-form-label": "Confirmation Message",
          "x-form-type": "text"
        },
        "errortext": {
          "type": "string",
          "x-source": "inject",
          "x-form-label": "Error Message",
          "x-form-type": "text"
        },
        "note": {
          "type": "string",
          "x-source": "inject",
          "x-form-label": "Note under the button",
          "x-form-type": "text"
        },
        "action": {
          "type": "string",
          "x-source": "inject",
          "x-form-label": "Form Action (mailto: or endpoint URL)",
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
              "x-form-name": "Immer sichtbar",
              "x-form-value": "all"
            },
            "desktop": {
              "x-form-name": "Nur Desktop (ab 821px)",
              "x-form-value": "desktop"
            },
            "mobile": {
              "x-form-name": "Nur Mobile (bis 820px)",
              "x-form-value": "mobile"
            }
          }
        },
        "focusx": {
          "type": "string",
          "x-source": "inject",
          "x-form-label": "Focus Point X (0 = links, 100 = rechts)",
          "x-form-type": "materialrange",
          "x-form-min": 0,
          "x-form-max": 100,
          "x-default": 50
        },
        "focusy": {
          "type": "string",
          "x-source": "inject",
          "x-form-label": "Focus Point Y (0 = oben, 100 = unten)",
          "x-form-type": "materialrange",
          "x-form-min": 0,
          "x-form-max": 100,
          "x-default": 50
        },
        "zoom": {
          "type": "string",
          "x-source": "inject",
          "x-form-label": "Zoom (100 = normal, bis 200%)",
          "x-form-type": "materialrange",
          "x-form-min": 100,
          "x-form-max": 200,
          "x-default": 100
        },
        "videourl": {
          "type": "string",
          "x-source": "inject",
          "x-form-label": "Vimeo Video (URL oder ID) – nutzt das Bild als Vorschau",
          "x-form-type": "text"
        },
        "videoautoplay": {
          "type": "string",
          "x-source": "inject",
          "x-form-label": "Video automatisch abspielen (stumm, in Schleife)",
          "x-form-type": "materialswitch"
        }
      }
    }
  },
  "name": "Contact",
  "componentPath": "bayviewthemevue3/components/contact",
  "package": "com.bayviewthemevue3.models",
  "modelName": "Contact",
  "classNameParent": "AbstractComponent"
}
//GEN]
*/

//GEN[:DEF
@Model(
        adaptables = Resource.class,
        resourceType = "bayviewthemevue3/components/contact",
        defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL,
        adapters = IComponent.class
)
@Exporter(
        name = "jackson",
        extensions = "json"
)

//GEN]
public class ContactModel extends AbstractComponent {

    public ContactModel(final Resource r) { super(r); }

    //GEN[:INJECT
    	/* {"type":"string","x-source":"inject","x-form-label":"Heading","x-form-type":"text"} */
	@Inject
	private String title;

	/* {"type":"string","x-source":"inject","x-form-label":"Side Photo","x-form-type":"pathbrowser","x-form-browserRoot":"/content/bayviewthemevue3/assets"} */
	@Inject
	private String image;

	/* {"type":"string","x-source":"inject","x-form-label":"Image Alt Text","x-form-type":"text"} */
	@Inject
	private String imagealt;

	/* {"type":"string","x-source":"inject","x-form-label":"Name Field Label","x-form-type":"text"} */
	@Inject
	private String namelabel;

	/* {"type":"string","x-source":"inject","x-form-label":"Email Field Label","x-form-type":"text"} */
	@Inject
	private String emaillabel;

	/* {"type":"string","x-source":"inject","x-form-label":"Message Field Label","x-form-type":"text"} */
	@Inject
	private String messagelabel;

	/* {"type":"string","x-source":"inject","x-form-label":"Button Label","x-form-type":"text"} */
	@Inject
	private String buttontext;

	/* {"type":"string","x-source":"inject","x-form-label":"Button Label While Sending","x-form-type":"text"} */
	@Inject
	private String sendingtext;

	/* {"type":"string","x-source":"inject","x-form-label":"Confirmation Message","x-form-type":"text"} */
	@Inject
	private String successtext;

	/* {"type":"string","x-source":"inject","x-form-label":"Error Message","x-form-type":"text"} */
	@Inject
	private String errortext;

	/* {"type":"string","x-source":"inject","x-form-label":"Note under the button","x-form-type":"text"} */
	@Inject
	private String note;

	/* {"type":"string","x-source":"inject","x-form-label":"Form Action (mailto: or endpoint URL)","x-form-type":"text"} */
	@Inject
	private String action;

	/* {"type":"string","x-source":"inject","x-form-label":"Swap Sides (mirror the two columns)","x-form-type":"materialswitch"} */
	@Inject
	private String swap;

	/* {"type":"string","x-source":"inject","x-form-label":"Visibility","x-form-type":"materialselect","x-default":"all","properties":{"all":{"x-form-name":"Immer sichtbar","x-form-value":"all"},"desktop":{"x-form-name":"Nur Desktop (ab 821px)","x-form-value":"desktop"},"mobile":{"x-form-name":"Nur Mobile (bis 820px)","x-form-value":"mobile"}}} */
	@Inject
	@Default(values ="all")
	private String visibility;

	/* {"type":"string","x-source":"inject","x-form-label":"Focus Point X (0 = links, 100 = rechts)","x-form-type":"materialrange","x-form-min":0,"x-form-max":100,"x-default":50} */
	@Inject
	@Default(values ="50")
	private String focusx;

	/* {"type":"string","x-source":"inject","x-form-label":"Focus Point Y (0 = oben, 100 = unten)","x-form-type":"materialrange","x-form-min":0,"x-form-max":100,"x-default":50} */
	@Inject
	@Default(values ="50")
	private String focusy;

	/* {"type":"string","x-source":"inject","x-form-label":"Zoom (100 = normal, bis 200%)","x-form-type":"materialrange","x-form-min":100,"x-form-max":200,"x-default":100} */
	@Inject
	@Default(values ="100")
	private String zoom;

	/* {"type":"string","x-source":"inject","x-form-label":"Vimeo Video (URL oder ID) – nutzt das Bild als Vorschau","x-form-type":"text"} */
	@Inject
	private String videourl;

	/* {"type":"string","x-source":"inject","x-form-label":"Video automatisch abspielen (stumm, in Schleife)","x-form-type":"materialswitch"} */
	@Inject
	private String videoautoplay;


//GEN]

    //GEN[:GETTERS
    	/* {"type":"string","x-source":"inject","x-form-label":"Heading","x-form-type":"text"} */
	public String getTitle() {
		return title;
	}

	/* {"type":"string","x-source":"inject","x-form-label":"Side Photo","x-form-type":"pathbrowser","x-form-browserRoot":"/content/bayviewthemevue3/assets"} */
	public String getImage() {
		return image;
	}

	/* {"type":"string","x-source":"inject","x-form-label":"Image Alt Text","x-form-type":"text"} */
	public String getImagealt() {
		return imagealt;
	}

	/* {"type":"string","x-source":"inject","x-form-label":"Name Field Label","x-form-type":"text"} */
	public String getNamelabel() {
		return namelabel;
	}

	/* {"type":"string","x-source":"inject","x-form-label":"Email Field Label","x-form-type":"text"} */
	public String getEmaillabel() {
		return emaillabel;
	}

	/* {"type":"string","x-source":"inject","x-form-label":"Message Field Label","x-form-type":"text"} */
	public String getMessagelabel() {
		return messagelabel;
	}

	/* {"type":"string","x-source":"inject","x-form-label":"Button Label","x-form-type":"text"} */
	public String getButtontext() {
		return buttontext;
	}

	/* {"type":"string","x-source":"inject","x-form-label":"Button Label While Sending","x-form-type":"text"} */
	public String getSendingtext() {
		return sendingtext;
	}

	/* {"type":"string","x-source":"inject","x-form-label":"Confirmation Message","x-form-type":"text"} */
	public String getSuccesstext() {
		return successtext;
	}

	/* {"type":"string","x-source":"inject","x-form-label":"Error Message","x-form-type":"text"} */
	public String getErrortext() {
		return errortext;
	}

	/* {"type":"string","x-source":"inject","x-form-label":"Note under the button","x-form-type":"text"} */
	public String getNote() {
		return note;
	}

	/* {"type":"string","x-source":"inject","x-form-label":"Form Action (mailto: or endpoint URL)","x-form-type":"text"} */
	public String getAction() {
		return action;
	}

	/* {"type":"string","x-source":"inject","x-form-label":"Swap Sides (mirror the two columns)","x-form-type":"materialswitch"} */
	public String getSwap() {
		return swap;
	}

	/* {"type":"string","x-source":"inject","x-form-label":"Visibility","x-form-type":"materialselect","x-default":"all","properties":{"all":{"x-form-name":"Immer sichtbar","x-form-value":"all"},"desktop":{"x-form-name":"Nur Desktop (ab 821px)","x-form-value":"desktop"},"mobile":{"x-form-name":"Nur Mobile (bis 820px)","x-form-value":"mobile"}}} */
	public String getVisibility() {
		return visibility;
	}

	/* {"type":"string","x-source":"inject","x-form-label":"Focus Point X (0 = links, 100 = rechts)","x-form-type":"materialrange","x-form-min":0,"x-form-max":100,"x-default":50} */
	public String getFocusx() {
		return focusx;
	}

	/* {"type":"string","x-source":"inject","x-form-label":"Focus Point Y (0 = oben, 100 = unten)","x-form-type":"materialrange","x-form-min":0,"x-form-max":100,"x-default":50} */
	public String getFocusy() {
		return focusy;
	}

	/* {"type":"string","x-source":"inject","x-form-label":"Zoom (100 = normal, bis 200%)","x-form-type":"materialrange","x-form-min":100,"x-form-max":200,"x-default":100} */
	public String getZoom() {
		return zoom;
	}

	/* {"type":"string","x-source":"inject","x-form-label":"Vimeo Video (URL oder ID) – nutzt das Bild als Vorschau","x-form-type":"text"} */
	public String getVideourl() {
		return videourl;
	}

	/* {"type":"string","x-source":"inject","x-form-label":"Video automatisch abspielen (stumm, in Schleife)","x-form-type":"materialswitch"} */
	public String getVideoautoplay() {
		return videoautoplay;
	}


//GEN]

    //GEN[:CUSTOMGETTERS
    //GEN]

}
