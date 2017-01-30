/*
	adc javascript utilities - v1.0.0
	- https://github.com/kkazo/adc.js - (c) 2017 ky.cho - licensed MIT
*/
(function (root, definition) {
	"use strict";
	if (typeof define === 'function' && define.amd) {
		define(definition);
	} else if (typeof module === 'object' && module.exports) {
		module.exports = definition();
	} else {
		root.adc = definition();
	}
}(this, function () {
	"use strict";

	function AdcUtilities() {
		var self = this;
		self.version = "1.0.0";
		self.init = function () {
			if (typeof window["init"] == "function") {
				window["init"]();
				log.info("page init called");
			} else {
				log.info("page init not defined");
			}
		};

		self.height = function (el) {
			var el_style = window.getComputedStyle(el),
				el_display = el_style.display,
				el_position  = el_style.position,
				el_visibility = el_style.visibility,
				el_max_height = el_style.maxHeight.replace('px', '').replace('%', '');

			if(el_display !== 'none' && el_max_height !== '0') {
				return el.offsetHeight;
			}
			el.style.position = 'absolute';
			el.style.visibility = 'hidden';
			el.style.display = 'block';
			var returnHeight = el.offsetHeight;
			el.style.display = el_display;
			el.style.position = el_position;
			el.style.visibility = el_visibility;

			return returnHeight;
		};

		self.animation =  {
			toggleSlide: function(el) {
				var el_max_height = 0;

				if (el.getAttribute('data-max-height')) {
					// we've already used this before, so everything is setup
					if (el.style.maxHeight.replace('px', '').replace('%', '') === '0') {
						el.style.maxHeight = el.getAttribute('data-max-height');
					} else {
						el.style.maxHeight = '0';
					}
				} else {
					el_max_height = self.height(el) + 'px';
					el.style['transition'] = 'max-height 500ms ease-in-out';
					el.style.overflowY = 'hidden';
					el.style.maxHeight = '0';
					el.setAttribute('data-max-height', el_max_height);
					el.style.display = 'block';

					// we use setTimeout to modify maxHeight later than display (to we have the transition effect)
					setTimeout(function () {
						el.style.maxHeight = el_max_height;
					}, 10);
				}
			}
		};
	}
	var adc = new AdcUtilities();
	window.adc = adc;
	return adc;

}));
