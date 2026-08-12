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
    "Blktable": {
      "type": "object",
      "x-type": "component",
      "properties": {
        "datelabel": {
          "type": "string",
          "x-source": "inject",
          "x-form-label": "First Column Label",
          "x-form-type": "text"
        },
        "eventlabel": {
          "type": "string",
          "x-source": "inject",
          "x-form-label": "Second Column Label",
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
  "name": "Blktable",
  "componentPath": "bayviewthemevue3/components/blktable",
  "package": "com.bayviewthemevue3.models",
  "modelName": "Blktable",
  "classNameParent": "AbstractComponent"
}
//GEN]
*/

//GEN[:DEF
@Model(
        adaptables = Resource.class,
        resourceType = "bayviewthemevue3/components/blktable",
        defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL,
        adapters = IComponent.class
)
@Exporter(
        name = "jackson",
        extensions = "json"
)

//GEN]
public class BlktableModel extends AbstractComponent {

    public BlktableModel(final Resource r) { super(r); }

    //GEN[:INJECT
    	/* {"type":"string","x-source":"inject","x-form-label":"First Column Label","x-form-type":"text"} */
	@Inject
	private String datelabel;

	/* {"type":"string","x-source":"inject","x-form-label":"Second Column Label","x-form-type":"text"} */
	@Inject
	private String eventlabel;

	/* {"type":"object","x-source":"inject","x-form-label":"Rows","x-form-type":"collection","x-form-fieldLabel":["date"],"properties":{"date":{"type":"string","x-source":"inject","x-form-label":"Date","x-form-type":"text"},"event":{"type":"string","x-source":"inject","x-form-label":"Event","x-form-type":"text"}}} */
	@Inject
	private List<IComponent> items;

	/* {"type":"string","x-source":"inject","x-form-label":"Visibility","x-form-type":"materialselect","x-default":"all","properties":{"all":{"x-form-name":"Immer sichtbar","x-form-value":"all"},"desktop":{"x-form-name":"Nur Desktop (ab 821px)","x-form-value":"desktop"},"mobile":{"x-form-name":"Nur Mobile (bis 820px)","x-form-value":"mobile"}}} */
	@Inject
	@Default(values ="all")
	private String visibility;


//GEN]

    //GEN[:GETTERS
    	/* {"type":"string","x-source":"inject","x-form-label":"First Column Label","x-form-type":"text"} */
	public String getDatelabel() {
		return datelabel;
	}

	/* {"type":"string","x-source":"inject","x-form-label":"Second Column Label","x-form-type":"text"} */
	public String getEventlabel() {
		return eventlabel;
	}

	/* {"type":"object","x-source":"inject","x-form-label":"Rows","x-form-type":"collection","x-form-fieldLabel":["date"],"properties":{"date":{"type":"string","x-source":"inject","x-form-label":"Date","x-form-type":"text"},"event":{"type":"string","x-source":"inject","x-form-label":"Event","x-form-type":"text"}}} */
	public List<IComponent> getItems() {
		return items;
	}

	/* {"type":"string","x-source":"inject","x-form-label":"Visibility","x-form-type":"materialselect","x-default":"all","properties":{"all":{"x-form-name":"Immer sichtbar","x-form-value":"all"},"desktop":{"x-form-name":"Nur Desktop (ab 821px)","x-form-value":"desktop"},"mobile":{"x-form-name":"Nur Mobile (bis 820px)","x-form-value":"mobile"}}} */
	public String getVisibility() {
		return visibility;
	}


//GEN]

    //GEN[:CUSTOMGETTERS
    //GEN]

}
