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
    "Threecols": {
      "type": "object",
      "x-type": "container",
      "properties": {
        "background": {
          "type": "string",
          "x-source": "inject",
          "x-form-label": "Background",
          "x-form-type": "materialselect",
          "x-default": "white",
          "properties": {
            "white": {
              "x-form-name": "White",
              "x-form-value": "white"
            },
            "cream": {
              "x-form-name": "Cream",
              "x-form-value": "cream"
            },
            "teal": {
              "x-form-name": "Teal",
              "x-form-value": "teal"
            }
          }
        },
        "title": {
          "type": "string",
          "x-source": "inject",
          "x-form-label": "Title",
          "x-form-type": "text"
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
  "name": "Threecols",
  "componentPath": "bayviewthemevue3/components/threecols",
  "package": "com.bayviewthemevue3.models",
  "modelName": "Threecols",
  "classNameParent": "Container"
}
//GEN]
*/

//GEN[:DEF
@Model(
        adaptables = Resource.class,
        resourceType = "bayviewthemevue3/components/threecols",
        defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL,
        adapters = IComponent.class
)
@Exporter(
        name = "jackson",
        extensions = "json"
)

//GEN]
public class ThreecolsModel extends Container {

    public ThreecolsModel(final Resource r) { super(r); }

    //GEN[:INJECT
    	/* {"type":"string","x-source":"inject","x-form-label":"Background","x-form-type":"materialselect","x-default":"white","properties":{"white":{"x-form-name":"White","x-form-value":"white"},"cream":{"x-form-name":"Cream","x-form-value":"cream"},"teal":{"x-form-name":"Teal","x-form-value":"teal"}}} */
	@Inject
	@Default(values ="white")
	private String background;

	/* {"type":"string","x-source":"inject","x-form-label":"Title","x-form-type":"text"} */
	@Inject
	private String title;

	/* {"type":"string","x-source":"inject","x-form-label":"Visibility","x-form-type":"materialselect","x-default":"all","properties":{"all":{"x-form-name":"Immer sichtbar","x-form-value":"all"},"desktop":{"x-form-name":"Nur Desktop (ab 821px)","x-form-value":"desktop"},"mobile":{"x-form-name":"Nur Mobile (bis 820px)","x-form-value":"mobile"}}} */
	@Inject
	@Default(values ="all")
	private String visibility;


//GEN]

    //GEN[:GETTERS
    	/* {"type":"string","x-source":"inject","x-form-label":"Background","x-form-type":"materialselect","x-default":"white","properties":{"white":{"x-form-name":"White","x-form-value":"white"},"cream":{"x-form-name":"Cream","x-form-value":"cream"},"teal":{"x-form-name":"Teal","x-form-value":"teal"}}} */
	public String getBackground() {
		return background;
	}

	/* {"type":"string","x-source":"inject","x-form-label":"Title","x-form-type":"text"} */
	public String getTitle() {
		return title;
	}

	/* {"type":"string","x-source":"inject","x-form-label":"Visibility","x-form-type":"materialselect","x-default":"all","properties":{"all":{"x-form-name":"Immer sichtbar","x-form-value":"all"},"desktop":{"x-form-name":"Nur Desktop (ab 821px)","x-form-value":"desktop"},"mobile":{"x-form-name":"Nur Mobile (bis 820px)","x-form-value":"mobile"}}} */
	public String getVisibility() {
		return visibility;
	}


//GEN]

    //GEN[:CUSTOMGETTERS
    //GEN]

}
