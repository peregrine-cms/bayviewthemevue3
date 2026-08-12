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
    "Header": {
      "type": "object",
      "x-type": "component",
      "properties": {
        "variant": {
          "type": "string",
          "x-source": "inject",
          "x-form-label": "Header Style",
          "x-form-type": "materialselect",
          "x-default": "default",
          "properties": {
            "default": {
              "x-form-name": "Lockup (mark + wordmark)",
              "x-form-value": "default"
            },
            "sticky": {
              "x-form-name": "Sticky (pins to top on scroll)",
              "x-form-value": "sticky"
            },
            "badge": {
              "x-form-name": "Badge (crest overhangs the content)",
              "x-form-value": "badge"
            },
            "centered": {
              "x-form-name": "Centered (crest above nav)",
              "x-form-value": "centered"
            }
          }
        },
        "logo": {
          "type": "string",
          "x-source": "inject",
          "x-form-label": "Logo Image (top left, shown next to the brand text)",
          "x-form-type": "pathbrowser",
          "x-form-browserRoot": "/content/bayviewthemevue3/assets"
        },
        "logoalt": {
          "type": "string",
          "x-source": "inject",
          "x-form-label": "Logo Alt Text",
          "x-form-type": "text"
        },
        "brand": {
          "type": "string",
          "x-source": "inject",
          "x-form-label": "Brand Wordmark",
          "x-form-type": "text"
        },
        "brandlink": {
          "type": "string",
          "x-source": "inject",
          "x-form-label": "Brand Link",
          "x-form-type": "pathbrowser",
          "x-form-browserRoot": "/content"
        },
        "navitems": {
          "type": "object",
          "x-source": "inject",
          "x-form-label": "Navigation Items",
          "x-form-type": "collection",
          "x-form-fieldLabel": [
            "text"
          ],
          "properties": {
            "text": {
              "type": "string",
              "x-source": "inject",
              "x-form-label": "Label",
              "x-form-type": "text"
            },
            "link": {
              "type": "string",
              "x-source": "inject",
              "x-form-label": "Link",
              "x-form-type": "pathbrowser",
              "x-form-browserRoot": "/content"
            }
          }
        },
        "internlink": {
          "type": "string",
          "x-source": "inject",
          "x-form-label": "Internal Area Link (blank = hide)",
          "x-form-type": "pathbrowser",
          "browserRoot": "/content"
        },
        "internlabel": {
          "type": "string",
          "x-source": "inject",
          "x-form-label": "Internal Area Label (logged in)",
          "x-form-type": "text"
        },
        "loginlabel": {
          "type": "string",
          "x-source": "inject",
          "x-form-label": "Internal Area Label (logged out)",
          "x-form-type": "text"
        },
        "shrink": {
          "type": "string",
          "x-source": "inject",
          "x-form-label": "Shrink logo on scroll (sticky and badge styles)",
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
  "name": "Header",
  "componentPath": "bayviewthemevue3/components/header",
  "package": "com.bayviewthemevue3.models",
  "modelName": "Header",
  "classNameParent": "AbstractComponent"
}
//GEN]
*/

//GEN[:DEF
@Model(
        adaptables = Resource.class,
        resourceType = "bayviewthemevue3/components/header",
        defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL,
        adapters = IComponent.class
)
@Exporter(
        name = "jackson",
        extensions = "json"
)

//GEN]
public class HeaderModel extends AbstractComponent {

    public HeaderModel(final Resource r) { super(r); }

    //GEN[:INJECT
    	/* {"type":"string","x-source":"inject","x-form-label":"Header Style","x-form-type":"materialselect","x-default":"default","properties":{"default":{"x-form-name":"Lockup (mark + wordmark)","x-form-value":"default"},"sticky":{"x-form-name":"Sticky (pins to top on scroll)","x-form-value":"sticky"},"badge":{"x-form-name":"Badge (crest overhangs the content)","x-form-value":"badge"},"centered":{"x-form-name":"Centered (crest above nav)","x-form-value":"centered"}}} */
	@Inject
	@Default(values ="default")
	private String variant;

	/* {"type":"string","x-source":"inject","x-form-label":"Logo Image (top left, shown next to the brand text)","x-form-type":"pathbrowser","x-form-browserRoot":"/content/bayviewthemevue3/assets"} */
	@Inject
	private String logo;

	/* {"type":"string","x-source":"inject","x-form-label":"Logo Alt Text","x-form-type":"text"} */
	@Inject
	private String logoalt;

	/* {"type":"string","x-source":"inject","x-form-label":"Brand Wordmark","x-form-type":"text"} */
	@Inject
	private String brand;

	/* {"type":"string","x-source":"inject","x-form-label":"Brand Link","x-form-type":"pathbrowser","x-form-browserRoot":"/content"} */
	@Inject
	private String brandlink;

	/* {"type":"object","x-source":"inject","x-form-label":"Navigation Items","x-form-type":"collection","x-form-fieldLabel":["text"],"properties":{"text":{"type":"string","x-source":"inject","x-form-label":"Label","x-form-type":"text"},"link":{"type":"string","x-source":"inject","x-form-label":"Link","x-form-type":"pathbrowser","x-form-browserRoot":"/content"}}} */
	@Inject
	private List<IComponent> navitems;

	/* {"type":"string","x-source":"inject","x-form-label":"Internal Area Link (blank = hide)","x-form-type":"pathbrowser","browserRoot":"/content"} */
	@Inject
	private String internlink;

	/* {"type":"string","x-source":"inject","x-form-label":"Internal Area Label (logged in)","x-form-type":"text"} */
	@Inject
	private String internlabel;

	/* {"type":"string","x-source":"inject","x-form-label":"Internal Area Label (logged out)","x-form-type":"text"} */
	@Inject
	private String loginlabel;

	/* {"type":"string","x-source":"inject","x-form-label":"Shrink logo on scroll (sticky and badge styles)","x-form-type":"materialswitch"} */
	@Inject
	private String shrink;

	/* {"type":"string","x-source":"inject","x-form-label":"Visibility","x-form-type":"materialselect","x-default":"all","properties":{"all":{"x-form-name":"Immer sichtbar","x-form-value":"all"},"desktop":{"x-form-name":"Nur Desktop (ab 821px)","x-form-value":"desktop"},"mobile":{"x-form-name":"Nur Mobile (bis 820px)","x-form-value":"mobile"}}} */
	@Inject
	@Default(values ="all")
	private String visibility;


//GEN]

    //GEN[:GETTERS
    	/* {"type":"string","x-source":"inject","x-form-label":"Header Style","x-form-type":"materialselect","x-default":"default","properties":{"default":{"x-form-name":"Lockup (mark + wordmark)","x-form-value":"default"},"sticky":{"x-form-name":"Sticky (pins to top on scroll)","x-form-value":"sticky"},"badge":{"x-form-name":"Badge (crest overhangs the content)","x-form-value":"badge"},"centered":{"x-form-name":"Centered (crest above nav)","x-form-value":"centered"}}} */
	public String getVariant() {
		return variant;
	}

	/* {"type":"string","x-source":"inject","x-form-label":"Logo Image (top left, shown next to the brand text)","x-form-type":"pathbrowser","x-form-browserRoot":"/content/bayviewthemevue3/assets"} */
	public String getLogo() {
		return logo;
	}

	/* {"type":"string","x-source":"inject","x-form-label":"Logo Alt Text","x-form-type":"text"} */
	public String getLogoalt() {
		return logoalt;
	}

	/* {"type":"string","x-source":"inject","x-form-label":"Brand Wordmark","x-form-type":"text"} */
	public String getBrand() {
		return brand;
	}

	/* {"type":"string","x-source":"inject","x-form-label":"Brand Link","x-form-type":"pathbrowser","x-form-browserRoot":"/content"} */
	public String getBrandlink() {
		return brandlink;
	}

	/* {"type":"object","x-source":"inject","x-form-label":"Navigation Items","x-form-type":"collection","x-form-fieldLabel":["text"],"properties":{"text":{"type":"string","x-source":"inject","x-form-label":"Label","x-form-type":"text"},"link":{"type":"string","x-source":"inject","x-form-label":"Link","x-form-type":"pathbrowser","x-form-browserRoot":"/content"}}} */
	public List<IComponent> getNavitems() {
		return navitems;
	}

	/* {"type":"string","x-source":"inject","x-form-label":"Internal Area Link (blank = hide)","x-form-type":"pathbrowser","browserRoot":"/content"} */
	public String getInternlink() {
		return internlink;
	}

	/* {"type":"string","x-source":"inject","x-form-label":"Internal Area Label (logged in)","x-form-type":"text"} */
	public String getInternlabel() {
		return internlabel;
	}

	/* {"type":"string","x-source":"inject","x-form-label":"Internal Area Label (logged out)","x-form-type":"text"} */
	public String getLoginlabel() {
		return loginlabel;
	}

	/* {"type":"string","x-source":"inject","x-form-label":"Shrink logo on scroll (sticky and badge styles)","x-form-type":"materialswitch"} */
	public String getShrink() {
		return shrink;
	}

	/* {"type":"string","x-source":"inject","x-form-label":"Visibility","x-form-type":"materialselect","x-default":"all","properties":{"all":{"x-form-name":"Immer sichtbar","x-form-value":"all"},"desktop":{"x-form-name":"Nur Desktop (ab 821px)","x-form-value":"desktop"},"mobile":{"x-form-name":"Nur Mobile (bis 820px)","x-form-value":"mobile"}}} */
	public String getVisibility() {
		return visibility;
	}


//GEN]

    //GEN[:CUSTOMGETTERS
    //GEN]

}
