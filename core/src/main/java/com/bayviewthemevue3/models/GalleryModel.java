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
    "Gallery": {
      "type": "object",
      "x-type": "component",
      "properties": {
        "text": {
          "type": "string",
          "x-source": "inject",
          "x-form-label": "Intro Text",
          "x-form-type": "texteditor"
        },
        "images": {
          "type": "object",
          "x-source": "inject",
          "x-form-label": "Images (first is the large one)",
          "x-form-type": "collection",
          "x-form-fieldLabel": [
            "imagealt"
          ],
          "properties": {
            "image": {
              "type": "string",
              "x-source": "inject",
              "x-form-label": "Image",
              "x-form-type": "pathbrowser",
              "x-form-browserRoot": "/content/bayviewthemevue3/assets"
            },
            "imagealt": {
              "type": "string",
              "x-source": "inject",
              "x-form-label": "Alt Text",
              "x-form-type": "text"
            }
          }
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
  "name": "Gallery",
  "componentPath": "bayviewthemevue3/components/gallery",
  "package": "com.bayviewthemevue3.models",
  "modelName": "Gallery",
  "classNameParent": "AbstractComponent"
}
//GEN]
*/

//GEN[:DEF
@Model(
        adaptables = Resource.class,
        resourceType = "bayviewthemevue3/components/gallery",
        defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL,
        adapters = IComponent.class
)
@Exporter(
        name = "jackson",
        extensions = "json"
)

//GEN]
public class GalleryModel extends AbstractComponent {

    public GalleryModel(final Resource r) { super(r); }

    //GEN[:INJECT
    	/* {"type":"string","x-source":"inject","x-form-label":"Intro Text","x-form-type":"texteditor"} */
	@Inject
	private String text;

	/* {"type":"object","x-source":"inject","x-form-label":"Images (first is the large one)","x-form-type":"collection","x-form-fieldLabel":["imagealt"],"properties":{"image":{"type":"string","x-source":"inject","x-form-label":"Image","x-form-type":"pathbrowser","x-form-browserRoot":"/content/bayviewthemevue3/assets"},"imagealt":{"type":"string","x-source":"inject","x-form-label":"Alt Text","x-form-type":"text"}}} */
	@Inject
	private List<IComponent> images;

	/* {"type":"string","x-source":"inject","x-form-label":"Swap Sides (mirror the two columns)","x-form-type":"materialswitch"} */
	@Inject
	private String swap;

	/* {"type":"string","x-source":"inject","x-form-label":"Visibility","x-form-type":"materialselect","x-default":"all","properties":{"all":{"x-form-name":"Immer sichtbar","x-form-value":"all"},"desktop":{"x-form-name":"Nur Desktop (ab 821px)","x-form-value":"desktop"},"mobile":{"x-form-name":"Nur Mobile (bis 820px)","x-form-value":"mobile"}}} */
	@Inject
	@Default(values ="all")
	private String visibility;


//GEN]

    //GEN[:GETTERS
    	/* {"type":"string","x-source":"inject","x-form-label":"Intro Text","x-form-type":"texteditor"} */
	public String getText() {
		return text;
	}

	/* {"type":"object","x-source":"inject","x-form-label":"Images (first is the large one)","x-form-type":"collection","x-form-fieldLabel":["imagealt"],"properties":{"image":{"type":"string","x-source":"inject","x-form-label":"Image","x-form-type":"pathbrowser","x-form-browserRoot":"/content/bayviewthemevue3/assets"},"imagealt":{"type":"string","x-source":"inject","x-form-label":"Alt Text","x-form-type":"text"}}} */
	public List<IComponent> getImages() {
		return images;
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
