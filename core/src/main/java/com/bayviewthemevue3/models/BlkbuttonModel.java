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
    "Blkbutton": {
      "type": "object",
      "x-type": "component",
      "properties": {
        "text": {
          "type": "string",
          "x-source": "inject",
          "x-form-label": "Button Label",
          "x-form-type": "text"
        },
        "link": {
          "type": "string",
          "x-source": "inject",
          "x-form-label": "Link",
          "x-form-type": "pathbrowser",
          "x-form-browserRoot": "/content"
        },
        "style": {
          "type": "string",
          "x-source": "inject",
          "x-form-label": "Style",
          "x-form-type": "materialselect",
          "x-default": "teal",
          "properties": {
            "teal": {
              "x-form-name": "Teal pill",
              "x-form-value": "teal"
            },
            "white": {
              "x-form-name": "White pill (on teal)",
              "x-form-value": "white"
            },
            "black": {
              "x-form-name": "Black bar",
              "x-form-value": "black"
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
  "name": "Blkbutton",
  "componentPath": "bayviewthemevue3/components/blkbutton",
  "package": "com.bayviewthemevue3.models",
  "modelName": "Blkbutton",
  "classNameParent": "AbstractComponent"
}
//GEN]
*/

//GEN[:DEF
@Model(
        adaptables = Resource.class,
        resourceType = "bayviewthemevue3/components/blkbutton",
        defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL,
        adapters = IComponent.class
)
@Exporter(
        name = "jackson",
        extensions = "json"
)

//GEN]
public class BlkbuttonModel extends AbstractComponent {

    public BlkbuttonModel(final Resource r) { super(r); }

    //GEN[:INJECT
    	/* {"type":"string","x-source":"inject","x-form-label":"Button Label","x-form-type":"text"} */
	@Inject
	private String text;

	/* {"type":"string","x-source":"inject","x-form-label":"Link","x-form-type":"pathbrowser","x-form-browserRoot":"/content"} */
	@Inject
	private String link;

	/* {"type":"string","x-source":"inject","x-form-label":"Style","x-form-type":"materialselect","x-default":"teal","properties":{"teal":{"x-form-name":"Teal pill","x-form-value":"teal"},"white":{"x-form-name":"White pill (on teal)","x-form-value":"white"},"black":{"x-form-name":"Black bar","x-form-value":"black"}}} */
	@Inject
	@Default(values ="teal")
	private String style;

	/* {"type":"string","x-source":"inject","x-form-label":"Visibility","x-form-type":"materialselect","x-default":"all","properties":{"all":{"x-form-name":"Always visible","x-form-value":"all"},"desktop":{"x-form-name":"Desktop only (821px and up)","x-form-value":"desktop"},"mobile":{"x-form-name":"Mobile only (up to 820px)","x-form-value":"mobile"}}} */
	@Inject
	@Default(values ="all")
	private String visibility;


//GEN]

    //GEN[:GETTERS
    	/* {"type":"string","x-source":"inject","x-form-label":"Button Label","x-form-type":"text"} */
	public String getText() {
		return text;
	}

	/* {"type":"string","x-source":"inject","x-form-label":"Link","x-form-type":"pathbrowser","x-form-browserRoot":"/content"} */
	public String getLink() {
		return link;
	}

	/* {"type":"string","x-source":"inject","x-form-label":"Style","x-form-type":"materialselect","x-default":"teal","properties":{"teal":{"x-form-name":"Teal pill","x-form-value":"teal"},"white":{"x-form-name":"White pill (on teal)","x-form-value":"white"},"black":{"x-form-name":"Black bar","x-form-value":"black"}}} */
	public String getStyle() {
		return style;
	}

	/* {"type":"string","x-source":"inject","x-form-label":"Visibility","x-form-type":"materialselect","x-default":"all","properties":{"all":{"x-form-name":"Always visible","x-form-value":"all"},"desktop":{"x-form-name":"Desktop only (821px and up)","x-form-value":"desktop"},"mobile":{"x-form-name":"Mobile only (up to 820px)","x-form-value":"mobile"}}} */
	public String getVisibility() {
		return visibility;
	}


//GEN]

    //GEN[:CUSTOMGETTERS
    //GEN]

}
