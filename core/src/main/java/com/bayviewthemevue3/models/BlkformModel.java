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
    "Blkform": {
      "type": "object",
      "x-type": "component",
      "properties": {
        "fields": {
          "type": "object",
          "x-source": "inject",
          "x-form-label": "Form Fields",
          "x-form-type": "collection",
          "x-form-fieldLabel": [
            "label"
          ],
          "properties": {
            "kind": {
              "type": "string",
              "x-source": "inject",
              "x-form-label": "Kind (input, email, textarea, options)",
              "x-form-type": "text"
            },
            "label": {
              "type": "string",
              "x-source": "inject",
              "x-form-label": "Label",
              "x-form-type": "text"
            },
            "options": {
              "type": "string",
              "x-source": "inject",
              "x-form-label": "Options (comma separated, for kind=options)",
              "x-form-type": "text"
            }
          }
        },
        "buttontext": {
          "type": "string",
          "x-source": "inject",
          "x-form-label": "Button Label",
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
        "style": {
          "type": "string",
          "x-source": "inject",
          "x-form-label": "Style",
          "x-form-type": "materialselect",
          "x-default": "light",
          "properties": {
            "light": {
              "x-form-name": "Light (outlined box, ink on white)",
              "x-form-value": "light"
            },
            "teal": {
              "x-form-name": "On teal (white outlined box)",
              "x-form-value": "teal"
            }
          }
        },
        "submitstyle": {
          "type": "string",
          "x-source": "inject",
          "x-form-label": "Submit Button",
          "x-form-type": "materialselect",
          "x-default": "black",
          "properties": {
            "black": {
              "x-form-name": "Black bar",
              "x-form-value": "black"
            },
            "white": {
              "x-form-name": "White pill",
              "x-form-value": "white"
            }
          }
        }
      }
    }
  },
  "name": "Blkform",
  "componentPath": "bayviewthemevue3/components/blkform",
  "package": "com.bayviewthemevue3.models",
  "modelName": "Blkform",
  "classNameParent": "AbstractComponent"
}
//GEN]
*/

//GEN[:DEF
@Model(
        adaptables = Resource.class,
        resourceType = "bayviewthemevue3/components/blkform",
        defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL,
        adapters = IComponent.class
)
@Exporter(
        name = "jackson",
        extensions = "json"
)

//GEN]
public class BlkformModel extends AbstractComponent {

    public BlkformModel(final Resource r) { super(r); }

    //GEN[:INJECT
    	/* {"type":"object","x-source":"inject","x-form-label":"Form Fields","x-form-type":"collection","x-form-fieldLabel":["label"],"properties":{"kind":{"type":"string","x-source":"inject","x-form-label":"Kind (input, email, textarea, options)","x-form-type":"text"},"label":{"type":"string","x-source":"inject","x-form-label":"Label","x-form-type":"text"},"options":{"type":"string","x-source":"inject","x-form-label":"Options (comma separated, for kind=options)","x-form-type":"text"}}} */
	@Inject
	private List<IComponent> fields;

	/* {"type":"string","x-source":"inject","x-form-label":"Button Label","x-form-type":"text"} */
	@Inject
	private String buttontext;

	/* {"type":"string","x-source":"inject","x-form-label":"Note under the button","x-form-type":"text"} */
	@Inject
	private String note;

	/* {"type":"string","x-source":"inject","x-form-label":"Form Action (mailto: or endpoint URL)","x-form-type":"text"} */
	@Inject
	private String action;

	/* {"type":"string","x-source":"inject","x-form-label":"Style","x-form-type":"materialselect","x-default":"light","properties":{"light":{"x-form-name":"Light (outlined box, ink on white)","x-form-value":"light"},"teal":{"x-form-name":"On teal (white outlined box)","x-form-value":"teal"}}} */
	@Inject
	@Default(values ="light")
	private String style;

	/* {"type":"string","x-source":"inject","x-form-label":"Submit Button","x-form-type":"materialselect","x-default":"black","properties":{"black":{"x-form-name":"Black bar","x-form-value":"black"},"white":{"x-form-name":"White pill","x-form-value":"white"}}} */
	@Inject
	@Default(values ="black")
	private String submitstyle;


//GEN]

    //GEN[:GETTERS
    	/* {"type":"object","x-source":"inject","x-form-label":"Form Fields","x-form-type":"collection","x-form-fieldLabel":["label"],"properties":{"kind":{"type":"string","x-source":"inject","x-form-label":"Kind (input, email, textarea, options)","x-form-type":"text"},"label":{"type":"string","x-source":"inject","x-form-label":"Label","x-form-type":"text"},"options":{"type":"string","x-source":"inject","x-form-label":"Options (comma separated, for kind=options)","x-form-type":"text"}}} */
	public List<IComponent> getFields() {
		return fields;
	}

	/* {"type":"string","x-source":"inject","x-form-label":"Button Label","x-form-type":"text"} */
	public String getButtontext() {
		return buttontext;
	}

	/* {"type":"string","x-source":"inject","x-form-label":"Note under the button","x-form-type":"text"} */
	public String getNote() {
		return note;
	}

	/* {"type":"string","x-source":"inject","x-form-label":"Form Action (mailto: or endpoint URL)","x-form-type":"text"} */
	public String getAction() {
		return action;
	}

	/* {"type":"string","x-source":"inject","x-form-label":"Style","x-form-type":"materialselect","x-default":"light","properties":{"light":{"x-form-name":"Light (outlined box, ink on white)","x-form-value":"light"},"teal":{"x-form-name":"On teal (white outlined box)","x-form-value":"teal"}}} */
	public String getStyle() {
		return style;
	}

	/* {"type":"string","x-source":"inject","x-form-label":"Submit Button","x-form-type":"materialselect","x-default":"black","properties":{"black":{"x-form-name":"Black bar","x-form-value":"black"},"white":{"x-form-name":"White pill","x-form-value":"white"}}} */
	public String getSubmitstyle() {
		return submitstyle;
	}


//GEN]

    //GEN[:CUSTOMGETTERS
    //GEN]

}
