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
    "Keydates": {
      "type": "object",
      "x-type": "component",
      "properties": {
        "title": {
          "type": "string",
          "x-source": "inject",
          "x-form-label": "Heading",
          "x-form-type": "text"
        },
        "datelabel": {
          "type": "string",
          "x-source": "inject",
          "x-form-label": "Date Column Label",
          "x-form-type": "text"
        },
        "eventlabel": {
          "type": "string",
          "x-source": "inject",
          "x-form-label": "Event Column Label",
          "x-form-type": "text"
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
        "items": {
          "type": "object",
          "x-source": "inject",
          "x-form-label": "Rows",
          "x-form-type": "collection",
          "x-form-fieldLabel": [
            "date"
          ],
          "properties": {
            "date": {
              "type": "string",
              "x-source": "inject",
              "x-form-label": "Date",
              "x-form-type": "text"
            },
            "event": {
              "type": "string",
              "x-source": "inject",
              "x-form-label": "Event",
              "x-form-type": "text"
            }
          }
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
        "source": {
          "type": "string",
          "x-source": "inject",
          "x-form-label": "Data source",
          "x-form-type": "materialselect",
          "x-default": "manual",
          "properties": {
            "manual": {
              "x-form-name": "Manual entries",
              "x-form-value": "manual"
            },
            "ical": {
              "x-form-name": "Google Calendar (all-day events)",
              "x-form-value": "ical"
            }
          }
        },
        "daysahead": {
          "type": "string",
          "x-source": "inject",
          "x-form-label": "Tage Vorschau (Kalender-Quelle)",
          "x-form-type": "materialrange",
          "x-form-min": 14,
          "x-form-max": 365,
          "x-default": 120
        },
        "maxitems": {
          "type": "string",
          "x-source": "inject",
          "x-form-label": "Maximum entries (0 = all)",
          "x-form-type": "text"
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
  "name": "Keydates",
  "componentPath": "bayviewthemevue3/components/keydates",
  "package": "com.bayviewthemevue3.models",
  "modelName": "Keydates",
  "classNameParent": "AbstractComponent"
}
//GEN]
*/

//GEN[:DEF
@Model(
        adaptables = Resource.class,
        resourceType = "bayviewthemevue3/components/keydates",
        defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL,
        adapters = IComponent.class
)
@Exporter(
        name = "jackson",
        extensions = "json"
)

//GEN]
public class KeydatesModel extends AbstractComponent {

    public KeydatesModel(final Resource r) { super(r); }

    //GEN[:INJECT
    	/* {"type":"string","x-source":"inject","x-form-label":"Heading","x-form-type":"text"} */
	@Inject
	private String title;

	/* {"type":"string","x-source":"inject","x-form-label":"Date Column Label","x-form-type":"text"} */
	@Inject
	private String datelabel;

	/* {"type":"string","x-source":"inject","x-form-label":"Event Column Label","x-form-type":"text"} */
	@Inject
	private String eventlabel;

	/* {"type":"string","x-source":"inject","x-form-label":"Image","x-form-type":"pathbrowser","x-form-browserRoot":"/content/bayviewthemevue3/assets"} */
	@Inject
	private String image;

	/* {"type":"string","x-source":"inject","x-form-label":"Image Alt Text","x-form-type":"text"} */
	@Inject
	private String imagealt;

	/* {"type":"object","x-source":"inject","x-form-label":"Rows","x-form-type":"collection","x-form-fieldLabel":["date"],"properties":{"date":{"type":"string","x-source":"inject","x-form-label":"Date","x-form-type":"text"},"event":{"type":"string","x-source":"inject","x-form-label":"Event","x-form-type":"text"}}} */
	@Inject
	private List<IComponent> items;

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

	/* {"type":"string","x-source":"inject","x-form-label":"Data source","x-form-type":"materialselect","x-default":"manual","properties":{"manual":{"x-form-name":"Manual entries","x-form-value":"manual"},"ical":{"x-form-name":"Google Calendar (all-day events)","x-form-value":"ical"}}} */
	@Inject
	@Default(values ="manual")
	private String source;

	/* {"type":"string","x-source":"inject","x-form-label":"Tage Vorschau (Kalender-Quelle)","x-form-type":"materialrange","x-form-min":14,"x-form-max":365,"x-default":120} */
	@Inject
	@Default(values ="120")
	private String daysahead;

	/* {"type":"string","x-source":"inject","x-form-label":"Maximum entries (0 = all)","x-form-type":"text"} */
	@Inject
	private String maxitems;

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

	/* {"type":"string","x-source":"inject","x-form-label":"Date Column Label","x-form-type":"text"} */
	public String getDatelabel() {
		return datelabel;
	}

	/* {"type":"string","x-source":"inject","x-form-label":"Event Column Label","x-form-type":"text"} */
	public String getEventlabel() {
		return eventlabel;
	}

	/* {"type":"string","x-source":"inject","x-form-label":"Image","x-form-type":"pathbrowser","x-form-browserRoot":"/content/bayviewthemevue3/assets"} */
	public String getImage() {
		return image;
	}

	/* {"type":"string","x-source":"inject","x-form-label":"Image Alt Text","x-form-type":"text"} */
	public String getImagealt() {
		return imagealt;
	}

	/* {"type":"object","x-source":"inject","x-form-label":"Rows","x-form-type":"collection","x-form-fieldLabel":["date"],"properties":{"date":{"type":"string","x-source":"inject","x-form-label":"Date","x-form-type":"text"},"event":{"type":"string","x-source":"inject","x-form-label":"Event","x-form-type":"text"}}} */
	public List<IComponent> getItems() {
		return items;
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

	/* {"type":"string","x-source":"inject","x-form-label":"Data source","x-form-type":"materialselect","x-default":"manual","properties":{"manual":{"x-form-name":"Manual entries","x-form-value":"manual"},"ical":{"x-form-name":"Google Calendar (all-day events)","x-form-value":"ical"}}} */
	public String getSource() {
		return source;
	}

	/* {"type":"string","x-source":"inject","x-form-label":"Tage Vorschau (Kalender-Quelle)","x-form-type":"materialrange","x-form-min":14,"x-form-max":365,"x-default":120} */
	public String getDaysahead() {
		return daysahead;
	}

	/* {"type":"string","x-source":"inject","x-form-label":"Maximum entries (0 = all)","x-form-type":"text"} */
	public String getMaxitems() {
		return maxitems;
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
