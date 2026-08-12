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
    "Register": {
      "type": "object",
      "x-type": "component",
      "properties": {
        "title": {
          "type": "string",
          "x-source": "inject",
          "x-form-label": "Heading",
          "x-form-type": "text"
        },
        "text": {
          "type": "string",
          "x-source": "inject",
          "x-form-label": "Text (bottom left)",
          "x-form-type": "texteditor"
        },
        "namelabel": {
          "type": "string",
          "x-source": "inject",
          "x-form-label": "Name Field Label",
          "x-form-type": "text"
        },
        "agelabel": {
          "type": "string",
          "x-source": "inject",
          "x-form-label": "Age Field Label",
          "x-form-type": "text"
        },
        "emaillabel": {
          "type": "string",
          "x-source": "inject",
          "x-form-label": "Email Field Label",
          "x-form-type": "text"
        },
        "leaguelabel": {
          "type": "string",
          "x-source": "inject",
          "x-form-label": "League Group Label",
          "x-form-type": "text"
        },
        "leagues": {
          "type": "object",
          "x-source": "inject",
          "x-form-label": "League Options",
          "x-form-type": "collection",
          "x-form-fieldLabel": [
            "text"
          ],
          "properties": {
            "text": {
              "type": "string",
              "x-source": "inject",
              "x-form-label": "Option",
              "x-form-type": "text"
            },
            "value": {
              "type": "string",
              "x-source": "inject",
              "x-form-label": "Value (optional, defaults to the option text)",
              "x-form-type": "text"
            }
          }
        },
        "levellabel": {
          "type": "string",
          "x-source": "inject",
          "x-form-label": "Level Group Label",
          "x-form-type": "text"
        },
        "levels": {
          "type": "object",
          "x-source": "inject",
          "x-form-label": "Level Options",
          "x-form-type": "collection",
          "x-form-fieldLabel": [
            "text"
          ],
          "properties": {
            "text": {
              "type": "string",
              "x-source": "inject",
              "x-form-label": "Option",
              "x-form-type": "text"
            },
            "value": {
              "type": "string",
              "x-source": "inject",
              "x-form-label": "Value (optional, defaults to the option text)",
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
        }
      }
    }
  },
  "name": "Register",
  "componentPath": "bayviewthemevue3/components/register",
  "package": "com.bayviewthemevue3.models",
  "modelName": "Register",
  "classNameParent": "AbstractComponent"
}
//GEN]
*/

//GEN[:DEF
@Model(
        adaptables = Resource.class,
        resourceType = "bayviewthemevue3/components/register",
        defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL,
        adapters = IComponent.class
)
@Exporter(
        name = "jackson",
        extensions = "json"
)

//GEN]
public class RegisterModel extends AbstractComponent {

    public RegisterModel(final Resource r) { super(r); }

    //GEN[:INJECT
    	/* {"type":"string","x-source":"inject","x-form-label":"Heading","x-form-type":"text"} */
	@Inject
	private String title;

	/* {"type":"string","x-source":"inject","x-form-label":"Text (bottom left)","x-form-type":"texteditor"} */
	@Inject
	private String text;

	/* {"type":"string","x-source":"inject","x-form-label":"Name Field Label","x-form-type":"text"} */
	@Inject
	private String namelabel;

	/* {"type":"string","x-source":"inject","x-form-label":"Age Field Label","x-form-type":"text"} */
	@Inject
	private String agelabel;

	/* {"type":"string","x-source":"inject","x-form-label":"Email Field Label","x-form-type":"text"} */
	@Inject
	private String emaillabel;

	/* {"type":"string","x-source":"inject","x-form-label":"League Group Label","x-form-type":"text"} */
	@Inject
	private String leaguelabel;

	/* {"type":"object","x-source":"inject","x-form-label":"League Options","x-form-type":"collection","x-form-fieldLabel":["text"],"properties":{"text":{"type":"string","x-source":"inject","x-form-label":"Option","x-form-type":"text"},"value":{"type":"string","x-source":"inject","x-form-label":"Value (optional, defaults to the option text)","x-form-type":"text"}}} */
	@Inject
	private List<IComponent> leagues;

	/* {"type":"string","x-source":"inject","x-form-label":"Level Group Label","x-form-type":"text"} */
	@Inject
	private String levellabel;

	/* {"type":"object","x-source":"inject","x-form-label":"Level Options","x-form-type":"collection","x-form-fieldLabel":["text"],"properties":{"text":{"type":"string","x-source":"inject","x-form-label":"Option","x-form-type":"text"},"value":{"type":"string","x-source":"inject","x-form-label":"Value (optional, defaults to the option text)","x-form-type":"text"}}} */
	@Inject
	private List<IComponent> levels;

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


//GEN]

    //GEN[:GETTERS
    	/* {"type":"string","x-source":"inject","x-form-label":"Heading","x-form-type":"text"} */
	public String getTitle() {
		return title;
	}

	/* {"type":"string","x-source":"inject","x-form-label":"Text (bottom left)","x-form-type":"texteditor"} */
	public String getText() {
		return text;
	}

	/* {"type":"string","x-source":"inject","x-form-label":"Name Field Label","x-form-type":"text"} */
	public String getNamelabel() {
		return namelabel;
	}

	/* {"type":"string","x-source":"inject","x-form-label":"Age Field Label","x-form-type":"text"} */
	public String getAgelabel() {
		return agelabel;
	}

	/* {"type":"string","x-source":"inject","x-form-label":"Email Field Label","x-form-type":"text"} */
	public String getEmaillabel() {
		return emaillabel;
	}

	/* {"type":"string","x-source":"inject","x-form-label":"League Group Label","x-form-type":"text"} */
	public String getLeaguelabel() {
		return leaguelabel;
	}

	/* {"type":"object","x-source":"inject","x-form-label":"League Options","x-form-type":"collection","x-form-fieldLabel":["text"],"properties":{"text":{"type":"string","x-source":"inject","x-form-label":"Option","x-form-type":"text"},"value":{"type":"string","x-source":"inject","x-form-label":"Value (optional, defaults to the option text)","x-form-type":"text"}}} */
	public List<IComponent> getLeagues() {
		return leagues;
	}

	/* {"type":"string","x-source":"inject","x-form-label":"Level Group Label","x-form-type":"text"} */
	public String getLevellabel() {
		return levellabel;
	}

	/* {"type":"object","x-source":"inject","x-form-label":"Level Options","x-form-type":"collection","x-form-fieldLabel":["text"],"properties":{"text":{"type":"string","x-source":"inject","x-form-label":"Option","x-form-type":"text"},"value":{"type":"string","x-source":"inject","x-form-label":"Value (optional, defaults to the option text)","x-form-type":"text"}}} */
	public List<IComponent> getLevels() {
		return levels;
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


//GEN]

    //GEN[:CUSTOMGETTERS
    //GEN]

}
