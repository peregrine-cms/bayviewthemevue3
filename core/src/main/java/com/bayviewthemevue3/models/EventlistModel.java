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
    "Eventlist": {
      "type": "object",
      "x-type": "component",
      "properties": {
        "title": {
          "type": "string",
          "x-source": "inject",
          "x-form-label": "Title",
          "x-form-type": "text"
        },
        "daysahead": {
          "type": "string",
          "x-source": "inject",
          "x-form-label": "Days to look ahead",
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
        "showlocation": {
          "type": "string",
          "x-source": "inject",
          "x-form-label": "Show location",
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
  "name": "Eventlist",
  "componentPath": "bayviewthemevue3/components/eventlist",
  "package": "com.bayviewthemevue3.models",
  "modelName": "Eventlist",
  "classNameParent": "AbstractComponent"
}
//GEN]
*/

//GEN[:DEF
@Model(
        adaptables = Resource.class,
        resourceType = "bayviewthemevue3/components/eventlist",
        defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL,
        adapters = IComponent.class
)
@Exporter(
        name = "jackson",
        extensions = "json"
)

//GEN]
public class EventlistModel extends AbstractComponent {

    public EventlistModel(final Resource r) { super(r); }

    //GEN[:INJECT
    	/* {"type":"string","x-source":"inject","x-form-label":"Title","x-form-type":"text"} */
	@Inject
	private String title;

	/* {"type":"string","x-source":"inject","x-form-label":"Days to look ahead","x-form-type":"materialrange","x-form-min":14,"x-form-max":365,"x-default":120} */
	@Inject
	@Default(values ="120")
	private String daysahead;

	/* {"type":"string","x-source":"inject","x-form-label":"Maximum entries (0 = all)","x-form-type":"text"} */
	@Inject
	private String maxitems;

	/* {"type":"string","x-source":"inject","x-form-label":"Show location","x-form-type":"materialswitch"} */
	@Inject
	private String showlocation;

	/* {"type":"string","x-source":"inject","x-form-label":"Visibility","x-form-type":"materialselect","x-default":"all","properties":{"all":{"x-form-name":"Immer sichtbar","x-form-value":"all"},"desktop":{"x-form-name":"Nur Desktop (ab 821px)","x-form-value":"desktop"},"mobile":{"x-form-name":"Nur Mobile (bis 820px)","x-form-value":"mobile"}}} */
	@Inject
	@Default(values ="all")
	private String visibility;


//GEN]

    //GEN[:GETTERS
    	/* {"type":"string","x-source":"inject","x-form-label":"Title","x-form-type":"text"} */
	public String getTitle() {
		return title;
	}

	/* {"type":"string","x-source":"inject","x-form-label":"Days to look ahead","x-form-type":"materialrange","x-form-min":14,"x-form-max":365,"x-default":120} */
	public String getDaysahead() {
		return daysahead;
	}

	/* {"type":"string","x-source":"inject","x-form-label":"Maximum entries (0 = all)","x-form-type":"text"} */
	public String getMaxitems() {
		return maxitems;
	}

	/* {"type":"string","x-source":"inject","x-form-label":"Show location","x-form-type":"materialswitch"} */
	public String getShowlocation() {
		return showlocation;
	}

	/* {"type":"string","x-source":"inject","x-form-label":"Visibility","x-form-type":"materialselect","x-default":"all","properties":{"all":{"x-form-name":"Immer sichtbar","x-form-value":"all"},"desktop":{"x-form-name":"Nur Desktop (ab 821px)","x-form-value":"desktop"},"mobile":{"x-form-name":"Nur Mobile (bis 820px)","x-form-value":"mobile"}}} */
	public String getVisibility() {
		return visibility;
	}


//GEN]

    //GEN[:CUSTOMGETTERS
    //GEN]

}
