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
    "Blkheading": {
      "type": "object",
      "x-type": "component",
      "properties": {
        "text": {
          "type": "string",
          "x-source": "inject",
          "x-form-label": "Title",
          "x-form-type": "text"
        },
        "size": {
          "type": "string",
          "x-source": "inject",
          "x-form-label": "Size",
          "x-form-type": "materialselect",
          "x-default": "band",
          "properties": {
            "display": {
              "x-form-name": "Display (hero)",
              "x-form-value": "display"
            },
            "band": {
              "x-form-name": "Band (section)",
              "x-form-value": "band"
            },
            "item": {
              "x-form-name": "Item (card)",
              "x-form-value": "item"
            }
          }
        },
        "level": {
          "type": "string",
          "x-source": "inject",
          "x-form-label": "Heading Level",
          "x-form-type": "materialselect",
          "x-default": "h2",
          "properties": {
            "h1": {
              "x-form-name": "H1",
              "x-form-value": "h1"
            },
            "h2": {
              "x-form-name": "H2",
              "x-form-value": "h2"
            },
            "h3": {
              "x-form-name": "H3",
              "x-form-value": "h3"
            }
          }
        },
        "overlay": {
          "type": "string",
          "x-source": "inject",
          "x-form-label": "Overlay (absolute top-left over the column media)",
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
  "name": "Blkheading",
  "componentPath": "bayviewthemevue3/components/blkheading",
  "package": "com.bayviewthemevue3.models",
  "modelName": "Blkheading",
  "classNameParent": "AbstractComponent"
}
//GEN]
*/

//GEN[:DEF
@Model(
        adaptables = Resource.class,
        resourceType = "bayviewthemevue3/components/blkheading",
        defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL,
        adapters = IComponent.class
)
@Exporter(
        name = "jackson",
        extensions = "json"
)

//GEN]
public class BlkheadingModel extends AbstractComponent {

    public BlkheadingModel(final Resource r) { super(r); }

    //GEN[:INJECT
    	/* {"type":"string","x-source":"inject","x-form-label":"Title","x-form-type":"text"} */
	@Inject
	private String text;

	/* {"type":"string","x-source":"inject","x-form-label":"Size","x-form-type":"materialselect","x-default":"band","properties":{"display":{"x-form-name":"Display (hero)","x-form-value":"display"},"band":{"x-form-name":"Band (section)","x-form-value":"band"},"item":{"x-form-name":"Item (card)","x-form-value":"item"}}} */
	@Inject
	@Default(values ="band")
	private String size;

	/* {"type":"string","x-source":"inject","x-form-label":"Heading Level","x-form-type":"materialselect","x-default":"h2","properties":{"h1":{"x-form-name":"H1","x-form-value":"h1"},"h2":{"x-form-name":"H2","x-form-value":"h2"},"h3":{"x-form-name":"H3","x-form-value":"h3"}}} */
	@Inject
	@Default(values ="h2")
	private String level;

	/* {"type":"string","x-source":"inject","x-form-label":"Overlay (absolute top-left over the column media)","x-form-type":"materialswitch"} */
	@Inject
	private String overlay;

	/* {"type":"string","x-source":"inject","x-form-label":"Visibility","x-form-type":"materialselect","x-default":"all","properties":{"all":{"x-form-name":"Immer sichtbar","x-form-value":"all"},"desktop":{"x-form-name":"Nur Desktop (ab 821px)","x-form-value":"desktop"},"mobile":{"x-form-name":"Nur Mobile (bis 820px)","x-form-value":"mobile"}}} */
	@Inject
	@Default(values ="all")
	private String visibility;


//GEN]

    //GEN[:GETTERS
    	/* {"type":"string","x-source":"inject","x-form-label":"Title","x-form-type":"text"} */
	public String getText() {
		return text;
	}

	/* {"type":"string","x-source":"inject","x-form-label":"Size","x-form-type":"materialselect","x-default":"band","properties":{"display":{"x-form-name":"Display (hero)","x-form-value":"display"},"band":{"x-form-name":"Band (section)","x-form-value":"band"},"item":{"x-form-name":"Item (card)","x-form-value":"item"}}} */
	public String getSize() {
		return size;
	}

	/* {"type":"string","x-source":"inject","x-form-label":"Heading Level","x-form-type":"materialselect","x-default":"h2","properties":{"h1":{"x-form-name":"H1","x-form-value":"h1"},"h2":{"x-form-name":"H2","x-form-value":"h2"},"h3":{"x-form-name":"H3","x-form-value":"h3"}}} */
	public String getLevel() {
		return level;
	}

	/* {"type":"string","x-source":"inject","x-form-label":"Overlay (absolute top-left over the column media)","x-form-type":"materialswitch"} */
	public String getOverlay() {
		return overlay;
	}

	/* {"type":"string","x-source":"inject","x-form-label":"Visibility","x-form-type":"materialselect","x-default":"all","properties":{"all":{"x-form-name":"Immer sichtbar","x-form-value":"all"},"desktop":{"x-form-name":"Nur Desktop (ab 821px)","x-form-value":"desktop"},"mobile":{"x-form-name":"Nur Mobile (bis 820px)","x-form-value":"mobile"}}} */
	public String getVisibility() {
		return visibility;
	}


//GEN]

    //GEN[:CUSTOMGETTERS
    //GEN]

}
