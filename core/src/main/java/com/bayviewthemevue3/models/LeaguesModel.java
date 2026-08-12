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
    "Leagues": {
      "type": "object",
      "x-type": "component",
      "properties": {
        "title": {
          "type": "string",
          "x-source": "inject",
          "x-form-label": "Heading",
          "x-form-type": "text"
        },
        "items": {
          "type": "object",
          "x-source": "inject",
          "x-form-label": "Columns",
          "x-form-type": "collection",
          "x-form-fieldLabel": [
            "title"
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
              "x-form-label": "Image Alt Text",
              "x-form-type": "text"
            },
            "title": {
              "type": "string",
              "x-source": "inject",
              "x-form-label": "Title",
              "x-form-type": "text"
            },
            "text": {
              "type": "string",
              "x-source": "inject",
              "x-form-label": "Text",
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
  "name": "Leagues",
  "componentPath": "bayviewthemevue3/components/leagues",
  "package": "com.bayviewthemevue3.models",
  "modelName": "Leagues",
  "classNameParent": "AbstractComponent"
}
//GEN]
*/

//GEN[:DEF
@Model(
        adaptables = Resource.class,
        resourceType = "bayviewthemevue3/components/leagues",
        defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL,
        adapters = IComponent.class
)
@Exporter(
        name = "jackson",
        extensions = "json"
)

//GEN]
public class LeaguesModel extends AbstractComponent {

    public LeaguesModel(final Resource r) { super(r); }

    //GEN[:INJECT
    	/* {"type":"string","x-source":"inject","x-form-label":"Heading","x-form-type":"text"} */
	@Inject
	private String title;

	/* {"type":"object","x-source":"inject","x-form-label":"Columns","x-form-type":"collection","x-form-fieldLabel":["title"],"properties":{"image":{"type":"string","x-source":"inject","x-form-label":"Image","x-form-type":"pathbrowser","x-form-browserRoot":"/content/bayviewthemevue3/assets"},"imagealt":{"type":"string","x-source":"inject","x-form-label":"Image Alt Text","x-form-type":"text"},"title":{"type":"string","x-source":"inject","x-form-label":"Title","x-form-type":"text"},"text":{"type":"string","x-source":"inject","x-form-label":"Text","x-form-type":"text"}}} */
	@Inject
	private List<IComponent> items;

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

	/* {"type":"object","x-source":"inject","x-form-label":"Columns","x-form-type":"collection","x-form-fieldLabel":["title"],"properties":{"image":{"type":"string","x-source":"inject","x-form-label":"Image","x-form-type":"pathbrowser","x-form-browserRoot":"/content/bayviewthemevue3/assets"},"imagealt":{"type":"string","x-source":"inject","x-form-label":"Image Alt Text","x-form-type":"text"},"title":{"type":"string","x-source":"inject","x-form-label":"Title","x-form-type":"text"},"text":{"type":"string","x-source":"inject","x-form-label":"Text","x-form-type":"text"}}} */
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
