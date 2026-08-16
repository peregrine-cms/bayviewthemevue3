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
    "Blkspacer": {
      "type": "object",
      "x-type": "component",
      "properties": {
        "size": {
          "type": "string",
          "x-source": "inject",
          "x-form-label": "Height",
          "x-form-type": "materialselect",
          "x-default": "medium",
          "properties": {
            "tiny": {
              "x-form-name": "Tiny (14px)",
              "x-form-value": "tiny"
            },
            "small": {
              "x-form-name": "Small (16px)",
              "x-form-value": "small"
            },
            "medium": {
              "x-form-name": "Medium (40px)",
              "x-form-value": "medium"
            },
            "large": {
              "x-form-name": "Large (80px)",
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
  "name": "Blkspacer",
  "componentPath": "bayviewthemevue3/components/blkspacer",
  "package": "com.bayviewthemevue3.models",
  "modelName": "Blkspacer",
  "classNameParent": "AbstractComponent"
}
//GEN]
*/

//GEN[:DEF
@Model(
        adaptables = Resource.class,
        resourceType = "bayviewthemevue3/components/blkspacer",
        defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL,
        adapters = IComponent.class
)
@Exporter(
        name = "jackson",
        extensions = "json"
)

//GEN]
public class BlkspacerModel extends AbstractComponent {

    public BlkspacerModel(final Resource r) { super(r); }

    //GEN[:INJECT
    	/* {"type":"string","x-source":"inject","x-form-label":"Height","x-form-type":"materialselect","x-default":"medium","properties":{"tiny":{"x-form-name":"Tiny (14px)","x-form-value":"tiny"},"small":{"x-form-name":"Small (16px)","x-form-value":"small"},"medium":{"x-form-name":"Medium (40px)","x-form-value":"medium"},"large":{"x-form-name":"Large (80px)","x-form-value":"large"}}} */
	@Inject
	@Default(values ="medium")
	private String size;

	/* {"type":"string","x-source":"inject","x-form-label":"Visibility","x-form-type":"materialselect","x-default":"all","properties":{"all":{"x-form-name":"Always visible","x-form-value":"all"},"desktop":{"x-form-name":"Desktop only (821px and up)","x-form-value":"desktop"},"mobile":{"x-form-name":"Mobile only (up to 820px)","x-form-value":"mobile"}}} */
	@Inject
	@Default(values ="all")
	private String visibility;


//GEN]

    //GEN[:GETTERS
    	/* {"type":"string","x-source":"inject","x-form-label":"Height","x-form-type":"materialselect","x-default":"medium","properties":{"tiny":{"x-form-name":"Tiny (14px)","x-form-value":"tiny"},"small":{"x-form-name":"Small (16px)","x-form-value":"small"},"medium":{"x-form-name":"Medium (40px)","x-form-value":"medium"},"large":{"x-form-name":"Large (80px)","x-form-value":"large"}}} */
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
