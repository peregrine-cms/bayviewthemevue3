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
    "Pagenav": {
      "type": "object",
      "x-type": "component",
      "properties": {
        "uplabel": {
          "type": "string",
          "x-source": "inject",
          "x-form-label": "Beschriftung des Übersicht-Links",
          "x-form-type": "text"
        },
        "uplink": {
          "type": "string",
          "x-source": "inject",
          "x-form-label": "Link",
          "x-form-type": "pathbrowser",
          "x-form-browserRoot": "/content"
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
  "name": "Pagenav",
  "componentPath": "bayviewthemevue3/components/pagenav",
  "package": "com.bayviewthemevue3.models",
  "modelName": "Pagenav",
  "classNameParent": "AbstractComponent"
}
//GEN]
*/

//GEN[:DEF
@Model(
        adaptables = Resource.class,
        resourceType = "bayviewthemevue3/components/pagenav",
        defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL,
        adapters = IComponent.class
)
@Exporter(
        name = "jackson",
        extensions = "json"
)

//GEN]
public class PagenavModel extends AbstractComponent {

    public PagenavModel(final Resource r) { super(r); }

    //GEN[:INJECT
    	/* {"type":"string","x-source":"inject","x-form-label":"Beschriftung des Übersicht-Links","x-form-type":"text"} */
	@Inject
	private String uplabel;

	/* {"type":"string","x-source":"inject","x-form-label":"Link","x-form-type":"pathbrowser","x-form-browserRoot":"/content"} */
	@Inject
	private String uplink;

	/* {"type":"string","x-source":"inject","x-form-label":"Visibility","x-form-type":"materialselect","x-default":"all","properties":{"all":{"x-form-name":"Immer sichtbar","x-form-value":"all"},"desktop":{"x-form-name":"Nur Desktop (ab 821px)","x-form-value":"desktop"},"mobile":{"x-form-name":"Nur Mobile (bis 820px)","x-form-value":"mobile"}}} */
	@Inject
	@Default(values ="all")
	private String visibility;


//GEN]

    //GEN[:GETTERS
    	/* {"type":"string","x-source":"inject","x-form-label":"Beschriftung des Übersicht-Links","x-form-type":"text"} */
	public String getUplabel() {
		return uplabel;
	}

	/* {"type":"string","x-source":"inject","x-form-label":"Link","x-form-type":"pathbrowser","x-form-browserRoot":"/content"} */
	public String getUplink() {
		return uplink;
	}

	/* {"type":"string","x-source":"inject","x-form-label":"Visibility","x-form-type":"materialselect","x-default":"all","properties":{"all":{"x-form-name":"Immer sichtbar","x-form-value":"all"},"desktop":{"x-form-name":"Nur Desktop (ab 821px)","x-form-value":"desktop"},"mobile":{"x-form-name":"Nur Mobile (bis 820px)","x-form-value":"mobile"}}} */
	public String getVisibility() {
		return visibility;
	}


//GEN]

    //GEN[:CUSTOMGETTERS
    //GEN]

}
