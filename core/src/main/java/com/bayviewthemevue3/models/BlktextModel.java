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
    "Blktext": {
      "type": "object",
      "x-type": "component",
      "properties": {
        "text": {
          "type": "string",
          "x-source": "inject",
          "x-form-label": "Text",
          "x-form-type": "texteditor"
        },
        "align": {
          "type": "string",
          "x-source": "inject",
          "x-form-label": "Placement",
          "x-form-type": "materialselect",
          "x-default": "left",
          "properties": {
            "left": {
              "x-form-name": "Left",
              "x-form-value": "left"
            },
            "right": {
              "x-form-name": "Right",
              "x-form-value": "right"
            },
            "center": {
              "x-form-name": "Center",
              "x-form-value": "center"
            }
          }
        },
        "size": {
          "type": "string",
          "x-source": "inject",
          "x-form-label": "Text Size",
          "x-form-type": "materialselect",
          "x-default": "normal",
          "properties": {
            "normal": {
              "x-form-name": "Normal (0.95rem)",
              "x-form-value": "normal"
            },
            "large": {
              "x-form-name": "Large (1.05rem)",
              "x-form-value": "large"
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
  "name": "Blktext",
  "componentPath": "bayviewthemevue3/components/blktext",
  "package": "com.bayviewthemevue3.models",
  "modelName": "Blktext",
  "classNameParent": "AbstractComponent"
}
//GEN]
*/

//GEN[:DEF
@Model(
        adaptables = Resource.class,
        resourceType = "bayviewthemevue3/components/blktext",
        defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL,
        adapters = IComponent.class
)
@Exporter(
        name = "jackson",
        extensions = "json"
)

//GEN]
public class BlktextModel extends AbstractComponent {

    public BlktextModel(final Resource r) { super(r); }

    //GEN[:INJECT
    	/* {"type":"string","x-source":"inject","x-form-label":"Text","x-form-type":"texteditor"} */
	@Inject
	private String text;

	/* {"type":"string","x-source":"inject","x-form-label":"Placement","x-form-type":"materialselect","x-default":"left","properties":{"left":{"x-form-name":"Left","x-form-value":"left"},"right":{"x-form-name":"Right","x-form-value":"right"},"center":{"x-form-name":"Center","x-form-value":"center"}}} */
	@Inject
	@Default(values ="left")
	private String align;

	/* {"type":"string","x-source":"inject","x-form-label":"Text Size","x-form-type":"materialselect","x-default":"normal","properties":{"normal":{"x-form-name":"Normal (0.95rem)","x-form-value":"normal"},"large":{"x-form-name":"Large (1.05rem)","x-form-value":"large"}}} */
	@Inject
	@Default(values ="normal")
	private String size;

	/* {"type":"string","x-source":"inject","x-form-label":"Visibility","x-form-type":"materialselect","x-default":"all","properties":{"all":{"x-form-name":"Always visible","x-form-value":"all"},"desktop":{"x-form-name":"Desktop only (821px and up)","x-form-value":"desktop"},"mobile":{"x-form-name":"Mobile only (up to 820px)","x-form-value":"mobile"}}} */
	@Inject
	@Default(values ="all")
	private String visibility;


//GEN]

    //GEN[:GETTERS
    	/* {"type":"string","x-source":"inject","x-form-label":"Text","x-form-type":"texteditor"} */
	public String getText() {
		return text;
	}

	/* {"type":"string","x-source":"inject","x-form-label":"Placement","x-form-type":"materialselect","x-default":"left","properties":{"left":{"x-form-name":"Left","x-form-value":"left"},"right":{"x-form-name":"Right","x-form-value":"right"},"center":{"x-form-name":"Center","x-form-value":"center"}}} */
	public String getAlign() {
		return align;
	}

	/* {"type":"string","x-source":"inject","x-form-label":"Text Size","x-form-type":"materialselect","x-default":"normal","properties":{"normal":{"x-form-name":"Normal (0.95rem)","x-form-value":"normal"},"large":{"x-form-name":"Large (1.05rem)","x-form-value":"large"}}} */
	public String getSize() {
		return size;
	}

	/* {"type":"string","x-source":"inject","x-form-label":"Visibility","x-form-type":"materialselect","x-default":"all","properties":{"all":{"x-form-name":"Always visible","x-form-value":"all"},"desktop":{"x-form-name":"Desktop only (821px and up)","x-form-value":"desktop"},"mobile":{"x-form-name":"Mobile only (up to 820px)","x-form-value":"mobile"}}} */
	public String getVisibility() {
		return visibility;
	}


//GEN]

    //GEN[:CUSTOMGETTERS
    //GEN]

}
