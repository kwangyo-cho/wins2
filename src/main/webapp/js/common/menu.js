/*
 menu - v1.0.0
 - https://github.com/kkazo/menu.js - (c) 2017 ky.cho - licensed MIT
 */
(function (root, definition) {
	"use strict";
	if (typeof define === 'function' && define.amd) {
		define(definition);
	} else if (typeof module === 'object' && module.exports) {
		module.exports = definition();
	} else {
		root.menu = definition();
	}
}(this, function () {
	"use strict";
	function MenuClass() {
		var self = this;
		const event =  {
			TOGGLE: "click.toggle"
		};
		self.version = "1.0.0";
		self.init = function (wrapper) {
			self.wrapper = wrapper;
			self.event.bind();
			return self;
		};
		self.event = {
			bind: function () {
				var items = self.wrapper.querySelectorAll("li");
				var hasChildItem = _.filter(items, function (o) {
					return o.querySelector("ul");
				}).forEach(function (item) {
					item.querySelector("a").addEventListener("click", function (e) {
						var childUl = item.querySelector("ul");
						adc.animation.toggleSlide(childUl);
						e.preventDefault();
					})
				});
			}
		}
	}
	window.menu = {
		wrapper: [],
		init: function (wrapper) {
			this.wrapper.push(new MenuClass().init(wrapper));
		}
	}
}));