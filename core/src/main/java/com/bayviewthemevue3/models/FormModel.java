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
    "Form": {
      "type": "object",
      "x-type": "component",
      "properties": {
        "schema": {
          "type": "string",
          "x-source": "inject",
          "x-form-label": "Form Schema (JSON Schema file)",
          "x-form-type": "pathbrowser",
          "x-form-browserRoot": "/content"
        },
        "uischema": {
          "type": "string",
          "x-source": "inject",
          "x-form-label": "UI Schema (JSON Forms UI schema file)",
          "x-form-type": "pathbrowser",
          "x-form-browserRoot": "/content"
        },
        "endpointurl": {
          "type": "string",
          "x-source": "inject",
          "x-form-label": "Submit endpoint URL (e.g. https://formspree.io/f/<id>)",
          "x-form-type": "text"
        },
        "submitfunction": {
          "type": "string",
          "x-source": "inject",
          "x-form-label": "Custom submit function (window.* path, optional)",
          "x-form-type": "text"
        },
        "submittext": {
          "type": "string",
          "x-source": "inject",
          "x-form-label": "Button Label",
          "x-form-type": "text"
        },
        "successmessage": {
          "type": "string",
          "x-source": "inject",
          "x-form-label": "Success Message (shown inline)",
          "x-form-type": "text"
        },
        "successpage": {
          "type": "string",
          "x-source": "inject",
          "x-form-label": "Success Page (optional, overrides message)",
          "x-form-type": "pathbrowser",
          "x-form-browserRoot": "/content"
        },
        "failmessage": {
          "type": "string",
          "x-source": "inject",
          "x-form-label": "Failure Message",
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
  "name": "Form",
  "componentPath": "bayviewthemevue3/components/form",
  "package": "com.bayviewthemevue3.models",
  "modelName": "Form",
  "classNameParent": "AbstractComponent"
}
//GEN]
*/

//GEN[:DEF
@Model(
        adaptables = Resource.class,
        resourceType = "bayviewthemevue3/components/form",
        defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL,
        adapters = IComponent.class
)
@Exporter(
        name = "jackson",
        extensions = "json"
)

//GEN]
public class FormModel extends AbstractComponent {

    public FormModel(final Resource r) { super(r); }

    //GEN[:INJECT
    	/* {"type":"string","x-source":"inject","x-form-label":"Form Schema (JSON Schema file)","x-form-type":"pathbrowser","x-form-browserRoot":"/content"} */
	@Inject
	private String schema;

	/* {"type":"string","x-source":"inject","x-form-label":"UI Schema (JSON Forms UI schema file)","x-form-type":"pathbrowser","x-form-browserRoot":"/content"} */
	@Inject
	private String uischema;

	/* {"type":"string","x-source":"inject","x-form-label":"Submit endpoint URL (e.g. https://formspree.io/f/<id>)","x-form-type":"text"} */
	@Inject
	private String endpointurl;

	/* {"type":"string","x-source":"inject","x-form-label":"Custom submit function (window.* path, optional)","x-form-type":"text"} */
	@Inject
	private String submitfunction;

	/* {"type":"string","x-source":"inject","x-form-label":"Button Label","x-form-type":"text"} */
	@Inject
	private String submittext;

	/* {"type":"string","x-source":"inject","x-form-label":"Success Message (shown inline)","x-form-type":"text"} */
	@Inject
	private String successmessage;

	/* {"type":"string","x-source":"inject","x-form-label":"Success Page (optional, overrides message)","x-form-type":"pathbrowser","x-form-browserRoot":"/content"} */
	@Inject
	private String successpage;

	/* {"type":"string","x-source":"inject","x-form-label":"Failure Message","x-form-type":"text"} */
	@Inject
	private String failmessage;

	/* {"type":"string","x-source":"inject","x-form-label":"Style","x-form-type":"materialselect","x-default":"light","properties":{"light":{"x-form-name":"Light (outlined box, ink on white)","x-form-value":"light"},"teal":{"x-form-name":"On teal (white outlined box)","x-form-value":"teal"}}} */
	@Inject
	@Default(values ="light")
	private String style;

	/* {"type":"string","x-source":"inject","x-form-label":"Submit Button","x-form-type":"materialselect","x-default":"black","properties":{"black":{"x-form-name":"Black bar","x-form-value":"black"},"white":{"x-form-name":"White pill","x-form-value":"white"}}} */
	@Inject
	@Default(values ="black")
	private String submitstyle;

	/* {"type":"string","x-source":"inject","x-form-label":"Visibility","x-form-type":"materialselect","x-default":"all","properties":{"all":{"x-form-name":"Always visible","x-form-value":"all"},"desktop":{"x-form-name":"Desktop only (821px and up)","x-form-value":"desktop"},"mobile":{"x-form-name":"Mobile only (up to 820px)","x-form-value":"mobile"}}} */
	@Inject
	@Default(values ="all")
	private String visibility;


//GEN]

    //GEN[:GETTERS
    	/* {"type":"string","x-source":"inject","x-form-label":"Form Schema (JSON Schema file)","x-form-type":"pathbrowser","x-form-browserRoot":"/content"} */
	public String getSchema() {
		return schema;
	}

	/* {"type":"string","x-source":"inject","x-form-label":"UI Schema (JSON Forms UI schema file)","x-form-type":"pathbrowser","x-form-browserRoot":"/content"} */
	public String getUischema() {
		return uischema;
	}

	/* {"type":"string","x-source":"inject","x-form-label":"Submit endpoint URL (e.g. https://formspree.io/f/<id>)","x-form-type":"text"} */
	public String getEndpointurl() {
		return endpointurl;
	}

	/* {"type":"string","x-source":"inject","x-form-label":"Custom submit function (window.* path, optional)","x-form-type":"text"} */
	public String getSubmitfunction() {
		return submitfunction;
	}

	/* {"type":"string","x-source":"inject","x-form-label":"Button Label","x-form-type":"text"} */
	public String getSubmittext() {
		return submittext;
	}

	/* {"type":"string","x-source":"inject","x-form-label":"Success Message (shown inline)","x-form-type":"text"} */
	public String getSuccessmessage() {
		return successmessage;
	}

	/* {"type":"string","x-source":"inject","x-form-label":"Success Page (optional, overrides message)","x-form-type":"pathbrowser","x-form-browserRoot":"/content"} */
	public String getSuccesspage() {
		return successpage;
	}

	/* {"type":"string","x-source":"inject","x-form-label":"Failure Message","x-form-type":"text"} */
	public String getFailmessage() {
		return failmessage;
	}

	/* {"type":"string","x-source":"inject","x-form-label":"Style","x-form-type":"materialselect","x-default":"light","properties":{"light":{"x-form-name":"Light (outlined box, ink on white)","x-form-value":"light"},"teal":{"x-form-name":"On teal (white outlined box)","x-form-value":"teal"}}} */
	public String getStyle() {
		return style;
	}

	/* {"type":"string","x-source":"inject","x-form-label":"Submit Button","x-form-type":"materialselect","x-default":"black","properties":{"black":{"x-form-name":"Black bar","x-form-value":"black"},"white":{"x-form-name":"White pill","x-form-value":"white"}}} */
	public String getSubmitstyle() {
		return submitstyle;
	}

	/* {"type":"string","x-source":"inject","x-form-label":"Visibility","x-form-type":"materialselect","x-default":"all","properties":{"all":{"x-form-name":"Always visible","x-form-value":"all"},"desktop":{"x-form-name":"Desktop only (821px and up)","x-form-value":"desktop"},"mobile":{"x-form-name":"Mobile only (up to 820px)","x-form-value":"mobile"}}} */
	public String getVisibility() {
		return visibility;
	}


//GEN]

    //GEN[:CUSTOMGETTERS
    //GEN]

}
