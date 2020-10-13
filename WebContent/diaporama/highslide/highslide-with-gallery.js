/******************************************************************************

Name:

    
    dze
    Highslide JS
Version: 4.1.7 (September 28 2009)
Config:  default +slideshow +positioning +transitions +viewport +thumbstrip
Author:  Torstein Hønsi
Support: http://highslide.com/support

Licence:
Highslide JS is licensed under a Creative Commons Attribution-NonCommercial 2.5
License (http://creativecommons.org/licenses/by-nc/2.5/).

You are free:
	* to copy, distribute, display, and perform the work
	* to make derivative works

Under the following conditions:
	* Attribution. You must attribute the work in the manner  specified by  the
	  author or licensor.
	* Noncommercial. You may not use this work for commercial purposes.

* For  any  reuse  or  distribution, you  must make clear to others the license
  terms of this work.
* Any  of  these  conditions  can  be  waived  if  you  get permission from the 
  copyright holder.

Your fair use and other rights are in no way affected by the above.
******************************************************************************/
if (!diaporama1) { var diaporama1 = {
// // Language strings
lang : {
	cssDirection: 'ltr',
	loadingText : 'Chargement...',
	loadingTitle : 'Cliquez pour quitter',
	focusTitle : 'Cliquez pour agrandir l image',
	fullExpandTitle : 'Taille reelle (f)',
	creditsText : 'Diaporama <i>R2T</i>',
	creditsTitle : 'R2T',
	previousText : 'Precedant',
	nextText : 'Suivant', 
	moveText : 'Deplacer',
	closeText : 'Fermer', 
	closeTitle : 'Fermer (esc)', 
	resizeTitle : 'Redimensionner',
	playText : 'Lire',
	playTitle : 'Lire (espace)',
	pauseText : 'Pause',
	pauseTitle : 'Pause Diapo (espace)',
	previousTitle : 'Precedant (Fleche gauche)',
	nextTitle : 'Suivant (Fleche droite)',
	moveTitle : 'Deplacer',
	fullExpandText : '1:1',
	number: 'Image %1 of %2',
	restoreTitle : 'Cliquez ici pour terminer le diaporama, bouger le curseur pour deplacer l image .Utilisez les fleches pour parcourir les images du diaporama.'
},
// See http://highslide.com/ref for examples of settings  
graphicsDir : 'highslide/graphics/',
expandCursor : 'zoomin.cur', // null disables
restoreCursor : 'zoomout.cur', // null disables
expandDuration : 250, // milliseconds
restoreDuration : 250,
marginLeft : 15,
marginRight : 15,
marginTop : 15,
marginBottom : 15,
zIndexCounter : 1001, // adjust to other absolutely positioned elements
loadingOpacity : 0.75,
allowMultipleInstances: true,
numberOfImagesToPreload : 5,
outlineWhileAnimating : 2, // 0 = never, 1 = always, 2 = HTML only 
outlineStartOffset : 3, // ends at 10
padToMinWidth : false, // pad the popup width to make room for wide caption
fullExpandPosition : 'bottom right',
fullExpandOpacity : 1,
showCredits : true, // you can set this to false if you want
creditsHref : 'http://highslide.com/',
creditsTarget : '_self',
enableKeyListener : true,
openerTagNames : ['a'], // Add more to allow slideshow indexing
transitions : [],
transitionDuration: 250,
dimmingOpacity: 0, // Lightbox style dimming background
dimmingDuration: 50, // 0 for instant dimming

anchor : 'auto', // where the image expands from
align : 'auto', // position in the client (overrides anchor)
targetX: null, // the id of a target element
targetY: null,
dragByHeading: true,
minWidth: 200,
minHeight: 200,
allowSizeReduction: true, // allow the image to reduce to fit client size. If false, this overrides minWidth and minHeight
outlineType : 'drop-shadow', // set null to disable outlines
skin : {
	controls:
		'<div class="highslide-controls"><ul>'+
			'<li class="highslide-previous">'+
				'<a href="#" title="{diaporama1.lang.previousTitle}">'+
				'<span>{diaporama1.lang.previousText}</span></a>'+
			'</li>'+
			'<li class="highslide-play">'+
				'<a href="#" title="{diaporama1.lang.playTitle}">'+
				'<span>{diaporama1.lang.playText}</span></a>'+
			'</li>'+
			'<li class="highslide-pause">'+
				'<a href="#" title="{diaporama1.lang.pauseTitle}">'+
				'<span>{diaporama1.lang.pauseText}</span></a>'+
			'</li>'+
			'<li class="highslide-next">'+
				'<a href="#" title="{diaporama1.lang.nextTitle}">'+
				'<span>{diaporama1.lang.nextText}</span></a>'+
			'</li>'+
			'<li class="highslide-move">'+
				'<a href="#" title="{diaporama1.lang.moveTitle}">'+
				'<span>{diaporama1.lang.moveText}</span></a>'+
			'</li>'+
			'<li class="highslide-full-expand">'+
				'<a href="#" title="{diaporama1.lang.fullExpandTitle}">'+
				'<span>{diaporama1.lang.fullExpandText}</span></a>'+
			'</li>'+
			'<li class="highslide-close">'+
				'<a href="#" title="{diaporama1.lang.closeTitle}" >'+
				'<span>{diaporama1.lang.closeText}</span></a>'+
			'</li>'+
		'</ul></div>'
},
// END OF YOUR SETTINGS


// declare internal properties
preloadTheseImages : [],
continuePreloading: true,
expanders : [],
overrides : [
	'allowSizeReduction',
	'useBox',
	'anchor',
	'align',
	'targetX',
	'targetY',
	'outlineType',
	'outlineWhileAnimating',
	'captionId',
	'captionText',
	'captionEval',
	'captionOverlay',
	'headingId',
	'headingText',
	'headingEval',
	'headingOverlay',
	'creditsPosition',
	'dragByHeading',
	'autoplay',
	'numberPosition',
	'transitions',
	'dimmingOpacity',
	
	'width',
	'height',
	
	'wrapperClassName',
	'minWidth',
	'minHeight',
	'maxWidth',
	'maxHeight',
	'slideshowGroup',
	'easing',
	'easingClose',
	'fadeInOut',
	'src'
],
overlays : [],
idCounter : 0,
oPos : {
	x: ['leftpanel', 'left', 'center', 'right', 'rightpanel'],
	y: ['above', 'top', 'middle', 'bottom', 'below']
},
mouse: {},
headingOverlay: {},
captionOverlay: {},
timers : [],

slideshows : [],

pendingOutlines : {},
clones : {},
onReady: [],
uaVersion: /Trident\/4\.0/.test(navigator.userAgent) ? 8 :
	parseFloat((navigator.userAgent.toLowerCase().match( /.+(?:rv|it|ra|ie)[\/: ]([\d.]+)/ ) || [0,'0'])[1]),
ie : (document.all && !window.opera),
safari : /Safari/.test(navigator.userAgent),
geckoMac : /Macintosh.+rv:1\.[0-8].+Gecko/.test(navigator.userAgent),

$ : function (id) {
	if (id) return document.getElementById(id);
},

push : function (arr, val) {
	arr[arr.length] = val;
},

createElement : function (tag, attribs, styles, parent, nopad) {
	var el = document.createElement(tag);
	if (attribs) diaporama1.extend(el, attribs);
	if (nopad) diaporama1.setStyles(el, {padding: 0, border: 'none', margin: 0});
	if (styles) diaporama1.setStyles(el, styles);
	if (parent) parent.appendChild(el);	
	return el;
},

extend : function (el, attribs) {
	for (var x in attribs) el[x] = attribs[x];
	return el;
},

setStyles : function (el, styles) {
	for (var x in styles) {
		if (diaporama1.ie && x == 'opacity') {
			if (styles[x] > 0.99) el.style.removeAttribute('filter');
			else el.style.filter = 'alpha(opacity='+ (styles[x] * 100) +')';
		}
		else el.style[x] = styles[x];
	}
},
animate: function(el, prop, opt) {
	var start,
		end,
		unit;
	if (typeof opt != 'object' || opt === null) {
		var args = arguments;
		opt = {
			duration: args[2],
			easing: args[3],
			complete: args[4]
		};
	}
	if (typeof opt.duration != 'number') opt.duration = 250;
	opt.easing = Math[opt.easing] || Math.easeInQuad;
	opt.curAnim = diaporama1.extend({}, prop);
	for (var name in prop) {
		var e = new diaporama1.fx(el, opt , name );
		
		start = parseFloat(diaporama1.css(el, name)) || 0;
		end = parseFloat(prop[name]);
		unit = name != 'opacity' ? 'px' : '';
		
		e.custom( start, end, unit );
	}	
},
css: function(el, prop) {
	if (document.defaultView) {
		return document.defaultView.getComputedStyle(el, null).getPropertyValue(prop);

	} else {
		if (prop == 'opacity') prop = 'filter';
		var val = el.currentStyle[prop.replace(/\-(\w)/g, function (a, b){ return b.toUpperCase(); })];
		if (prop == 'filter') 
			val = val.replace(/alpha\(opacity=([0-9]+)\)/, 
				function (a, b) { return b / 100 });
		return val === '' ? 1 : val;
	} 
},

getPageSize : function () {
	var d = document, w = window, iebody = d.compatMode && d.compatMode != 'BackCompat' 
		? d.documentElement : d.body;
	
	var width = diaporama1.ie ? iebody.clientWidth : 
			(d.documentElement.clientWidth || self.innerWidth),
		height = diaporama1.ie ? iebody.clientHeight : self.innerHeight;
	
	diaporama1.page = {
		width: width,
		height: height,		
		scrollLeft: diaporama1.ie ? iebody.scrollLeft : pageXOffset,
		scrollTop: diaporama1.ie ? iebody.scrollTop : pageYOffset
	}
},

getPosition : function(el)	{
	var p = { x: el.offsetLeft, y: el.offsetTop };
	while (el.offsetParent)	{
		el = el.offsetParent;
		p.x += el.offsetLeft;
		p.y += el.offsetTop;
		if (el != document.body && el != document.documentElement) {
			p.x -= el.scrollLeft;
			p.y -= el.scrollTop;
		}
	}
	return p;
},

expand : function(a, params, custom, type) {
	if (!a) a = diaporama1.createElement('a', null, { display: 'none' }, diaporama1.container);
	if (typeof a.getParams == 'function') return params;	
	try {	
		new diaporama1.Expander(a, params, custom);
		return false;
	} catch (e) { return true; }
},
getElementByClass : function (el, tagName, className) {
	var els = el.getElementsByTagName(tagName);
	for (var i = 0; i < els.length; i++) {
    	if ((new RegExp(className)).test(els[i].className)) {
			return els[i];
		}
	}
	return null;
},
replaceLang : function(s) {
	s = s.replace(/\s/g, ' ');
	var re = /{diaporama1\.lang\.([^}]+)\}/g,
		matches = s.match(re),
		lang;
	if (matches) for (var i = 0; i < matches.length; i++) {
		lang = matches[i].replace(re, "$1");
		if (typeof diaporama1.lang[lang] != 'undefined') s = s.replace(matches[i], diaporama1.lang[lang]);
	}
	return s;
},


focusTopmost : function() {
	var topZ = 0, 
		topmostKey = -1,
		expanders = diaporama1.expanders,
		exp,
		zIndex;
	for (var i = 0; i < expanders.length; i++) {
		exp = expanders[i];
		if (exp) {
			zIndex = exp.wrapper.style.zIndex;
			if (zIndex && zIndex > topZ) {
				topZ = zIndex;				
				topmostKey = i;
			}
		}
	}
	if (topmostKey == -1) diaporama1.focusKey = -1;
	else expanders[topmostKey].focus();
},

getParam : function (a, param) {
	a.getParams = a.onclick;
	var p = a.getParams ? a.getParams() : null;
	a.getParams = null;
	
	return (p && typeof p[param] != 'undefined') ? p[param] : 
		(typeof diaporama1[param] != 'undefined' ? diaporama1[param] : null);
},

getSrc : function (a) {
	var src = diaporama1.getParam(a, 'src');
	if (src) return src;
	return a.href;
},

getNode : function (id) {
	var node = diaporama1.$(id), clone = diaporama1.clones[id], a = {};
	if (!node && !clone) return null;
	if (!clone) {
		clone = node.cloneNode(true);
		clone.id = '';
		diaporama1.clones[id] = clone;
		return node;
	} else {
		return clone.cloneNode(true);
	}
},

discardElement : function(d) {
	if (d) diaporama1.garbageBin.appendChild(d);
	diaporama1.garbageBin.innerHTML = '';
},
dim : function(exp) {
	function fix(prop) {
		return 'expression( ( ( ignoreMe = document.documentElement.'+ prop +
			' ? document.documentElement.'+ prop +' : document.body.'+ prop +' ) ) + \'px\' );';
	}

	if (!diaporama1.dimmer) {
		diaporama1.dimmer = diaporama1.createElement ('div', {
				className: 'highslide-dimming highslide-viewport-size',
				owner: '',
				onclick: function() {
					
						diaporama1.close();
				}
			}, {
                visibility: 'visible',
				opacity: 0
			}, diaporama1.container, true);
	}

	diaporama1.dimmer.style.display = '';

	diaporama1.dimmer.owner += '|'+ exp.key;
	if (diaporama1.geckoMac && diaporama1.dimmingGeckoFix)
		diaporama1.setStyles(diaporama1.dimmer, {
			background: 'url('+ diaporama1.graphicsDir + 'geckodimmer.png)',
			opacity: 1
		});
	else
		diaporama1.animate(diaporama1.dimmer, { opacity: exp.dimmingOpacity }, diaporama1.dimmingDuration);
},
undim : function(key) {
	if (!diaporama1.dimmer) return;
	if (typeof key != 'undefined') diaporama1.dimmer.owner = diaporama1.dimmer.owner.replace('|'+ key, '');

	if (
		(typeof key != 'undefined' && diaporama1.dimmer.owner != '')
		|| (diaporama1.upcoming && diaporama1.getParam(diaporama1.upcoming, 'dimmingOpacity'))
	) return;

	if (diaporama1.geckoMac && diaporama1.dimmingGeckoFix) diaporama1.dimmer.style.display = 'none';
	else diaporama1.animate(diaporama1.dimmer, { opacity: 0 }, diaporama1.dimmingDuration, null, function() {
		diaporama1.dimmer.style.display = 'none';
	});
},
transit : function (adj, exp) {
	var last = exp = exp || diaporama1.getExpander();
	if (diaporama1.upcoming) return false;
	else diaporama1.last = last;
	try {
		diaporama1.upcoming = adj;
		adj.onclick(); 		
	} catch (e){
		diaporama1.last = diaporama1.upcoming = null;
	}
	try {
		if (!adj || exp.transitions[1] != 'crossfade')
		exp.close();
	} catch (e) {}
	return false;
},

previousOrNext : function (el, op) {
	var exp = diaporama1.getExpander(el);
	if (exp) return diaporama1.transit(exp.getAdjacentAnchor(op), exp);
	else return false;
},

previous : function (el) {
	return diaporama1.previousOrNext(el, -1);
},

next : function (el) {
	return diaporama1.previousOrNext(el, 1);	
},

keyHandler : function(e) {
	if (!e) e = window.event;
	if (!e.target) e.target = e.srcElement; // ie
	if (typeof e.target.form != 'undefined') return true; // form element has focus
	var exp = diaporama1.getExpander();
	
	var op = null;
	switch (e.keyCode) {
		case 70: // f
			if (exp) exp.doFullExpand();
			return true;
		case 32: // Space
			op = 2;
			break;
		case 34: // Page Down
		case 39: // Arrow right
		case 40: // Arrow down
			op = 1;
			break;
		case 8:  // Backspace
		case 33: // Page Up
		case 37: // Arrow left
		case 38: // Arrow up
			op = -1;
			break;
		case 27: // Escape
		case 13: // Enter
			op = 0;
	}
	if (op !== null) {if (op != 2)diaporama1.removeEventListener(document, window.opera ? 'keypress' : 'keydown', diaporama1.keyHandler);
		if (!diaporama1.enableKeyListener) return true;
		
		if (e.preventDefault) e.preventDefault();
    	else e.returnValue = false;
    	if (exp) {
			if (op == 0) {
				exp.close();
			} else if (op == 2) {
				if (exp.slideshow) exp.slideshow.hitSpace();
			} else {
				if (exp.slideshow) exp.slideshow.pause();
				diaporama1.previousOrNext(exp.key, op);
			}
			return false;
		}
	}
	return true;
},


registerOverlay : function (overlay) {
	diaporama1.push(diaporama1.overlays, diaporama1.extend(overlay, { hsId: 'hsId'+ diaporama1.idCounter++ } ));
},


addSlideshow : function (options) {
	var sg = options.slideshowGroup;
	if (typeof sg == 'object') {
		for (var i = 0; i < sg.length; i++) {
			var o = {};
			for (var x in options) o[x] = options[x];
			o.slideshowGroup = sg[i];
			diaporama1.push(diaporama1.slideshows, o);
		}
	} else {
		diaporama1.push(diaporama1.slideshows, options);
	}
},

getWrapperKey : function (element, expOnly) {
	var el, re = /^highslide-wrapper-([0-9]+)$/;
	// 1. look in open expanders
	el = element;
	while (el.parentNode)	{
		if (el.hsKey !== undefined) return el.hsKey;
		if (el.id && re.test(el.id)) return el.id.replace(re, "$1");
		el = el.parentNode;
	}
	// 2. look in thumbnail
	if (!expOnly) {
		el = element;
		while (el.parentNode)	{
			if (el.tagName && diaporama1.isHsAnchor(el)) {
				for (var key = 0; key < diaporama1.expanders.length; key++) {
					var exp = diaporama1.expanders[key];
					if (exp && exp.a == el) return key;
				}
			}
			el = el.parentNode;
		}
	}
	return null; 
},

getExpander : function (el, expOnly) {
	if (typeof el == 'undefined') return diaporama1.expanders[diaporama1.focusKey] || null;
	if (typeof el == 'number') return diaporama1.expanders[el] || null;
	if (typeof el == 'string') el = diaporama1.$(el);
	return diaporama1.expanders[diaporama1.getWrapperKey(el, expOnly)] || null;
},

isHsAnchor : function (a) {
	return (a.onclick && a.onclick.toString().replace(/\s/g, ' ').match(/diaporama1.(htmlE|e)xpand/));
},

reOrder : function () {
	for (var i = 0; i < diaporama1.expanders.length; i++)
		if (diaporama1.expanders[i] && diaporama1.expanders[i].isExpanded) diaporama1.focusTopmost();
},

mouseClickHandler : function(e) 
{	
	if (!e) e = window.event;
	if (e.button > 1) return true;
	if (!e.target) e.target = e.srcElement;
	
	var el = e.target;
	while (el.parentNode
		&& !(/highslide-(image|move|html|resize)/.test(el.className)))
	{
		el = el.parentNode;
	}
	var exp = diaporama1.getExpander(el);
	if (exp && (exp.isClosing || !exp.isExpanded)) return true;
		
	if (exp && e.type == 'mousedown') {
		if (e.target.form) return true;
		var match = el.className.match(/highslide-(image|move|resize)/);
		if (match) {
			diaporama1.dragArgs = { exp: exp , type: match[1], left: exp.x.pos, width: exp.x.size, top: exp.y.pos, 
				height: exp.y.size, clickX: e.clientX, clickY: e.clientY };
			
			
			diaporama1.addEventListener(document, 'mousemove', diaporama1.dragHandler);
			if (e.preventDefault) e.preventDefault(); // FF
			
			if (/highslide-(image|html)-blur/.test(exp.content.className)) {
				exp.focus();
				diaporama1.hasFocused = true;
			}
			return false;
		}
	} else if (e.type == 'mouseup') {
		
		diaporama1.removeEventListener(document, 'mousemove', diaporama1.dragHandler);
		
		if (diaporama1.dragArgs) {
			if (diaporama1.styleRestoreCursor && diaporama1.dragArgs.type == 'image') 
				diaporama1.dragArgs.exp.content.style.cursor = diaporama1.styleRestoreCursor;
			var hasDragged = diaporama1.dragArgs.hasDragged;
			
			if (!hasDragged &&!diaporama1.hasFocused && !/(move|resize)/.test(diaporama1.dragArgs.type)) {
				exp.close();
			} 
			else if (hasDragged || (!hasDragged && diaporama1.hasHtmlExpanders)) {
				diaporama1.dragArgs.exp.doShowHide('hidden');
			}
			diaporama1.hasFocused = false;
			diaporama1.dragArgs = null;
		
		} else if (/highslide-image-blur/.test(el.className)) {
			el.style.cursor = diaporama1.styleRestoreCursor;		
		}
	}
	return false;
},

dragHandler : function(e)
{
	if (!diaporama1.dragArgs) return true;
	if (!e) e = window.event;
	var a = diaporama1.dragArgs, exp = a.exp;
	
	a.dX = e.clientX - a.clickX;
	a.dY = e.clientY - a.clickY;	
	
	var distance = Math.sqrt(Math.pow(a.dX, 2) + Math.pow(a.dY, 2));
	if (!a.hasDragged) a.hasDragged = (a.type != 'image' && distance > 0)
		|| (distance > (diaporama1.dragSensitivity || 5));
	
	if (a.hasDragged && e.clientX > 5 && e.clientY > 5) {
		
		if (a.type == 'resize') exp.resize(a);
		else {
			exp.moveTo(a.left + a.dX, a.top + a.dY);
			if (a.type == 'image') exp.content.style.cursor = 'move';
		}
	}
	return false;
},

wrapperMouseHandler : function (e) {
	try {
		if (!e) e = window.event;
		var over = /mouseover/i.test(e.type); 
		if (!e.target) e.target = e.srcElement; // ie
		if (diaporama1.ie) e.relatedTarget = 
			over ? e.fromElement : e.toElement; // ie
		var exp = diaporama1.getExpander(e.target);
		if (!exp.isExpanded) return;
		if (!exp || !e.relatedTarget || diaporama1.getExpander(e.relatedTarget, true) == exp 
			|| diaporama1.dragArgs) return;
		for (var i = 0; i < exp.overlays.length; i++) (function() {
			var o = diaporama1.$('hsId'+ exp.overlays[i]);
			if (o && o.hideOnMouseOut) {
				if (over) diaporama1.setStyles(o, { visibility: 'visible', display: '' });
				diaporama1.animate(o, { opacity: over ? o.opacity : 0 }, o.dur);
			}
		})();	
	} catch (e) {}
},
addEventListener : function (el, event, func) {
	if (el == document && event == 'ready') diaporama1.push(diaporama1.onReady, func);
	try {
		el.addEventListener(event, func, false);
	} catch (e) {
		try {
			el.detachEvent('on'+ event, func);
			el.attachEvent('on'+ event, func);
		} catch (e) {
			el['on'+ event] = func;
		}
	} 
},

removeEventListener : function (el, event, func) {
	try {
		el.removeEventListener(event, func, false);
	} catch (e) {
		try {
			el.detachEvent('on'+ event, func);
		} catch (e) {
			el['on'+ event] = null;
		}
	}
},

preloadFullImage : function (i) {
	if (diaporama1.continuePreloading && diaporama1.preloadTheseImages[i] && diaporama1.preloadTheseImages[i] != 'undefined') {
		var img = document.createElement('img');
		img.onload = function() { 
			img = null;
			diaporama1.preloadFullImage(i + 1);
		};
		img.src = diaporama1.preloadTheseImages[i];
	}
},
preloadImages : function (number) {
	if (number && typeof number != 'object') diaporama1.numberOfImagesToPreload = number;
	
	var arr = diaporama1.getAnchors();
	for (var i = 0; i < arr.images.length && i < diaporama1.numberOfImagesToPreload; i++) {
		diaporama1.push(diaporama1.preloadTheseImages, diaporama1.getSrc(arr.images[i]));
	}
	
	// preload outlines
	if (diaporama1.outlineType)	new diaporama1.Outline(diaporama1.outlineType, function () { diaporama1.preloadFullImage(0)} );
	else
	
	diaporama1.preloadFullImage(0);
	
	// preload cursor
	if (diaporama1.restoreCursor) var cur = diaporama1.createElement('img', { src: diaporama1.graphicsDir + diaporama1.restoreCursor });
},


init : function () {
	if (!diaporama1.container) {
	
		diaporama1.getPageSize();
		diaporama1.ieLt7 = diaporama1.ie && diaporama1.uaVersion < 7;
		for (var x in diaporama1.langDefaults) {
			if (typeof diaporama1[x] != 'undefined') diaporama1.lang[x] = diaporama1[x];
			else if (typeof diaporama1.lang[x] == 'undefined' && typeof diaporama1.langDefaults[x] != 'undefined') 
				diaporama1.lang[x] = diaporama1.langDefaults[x];
		}
		
		diaporama1.container = diaporama1.createElement('div', {
				className: 'highslide-container'
			}, {
				position: 'absolute', 
				left: 0, 
				top: 0, 
				width: '100%', 
				zIndex: diaporama1.zIndexCounter,
				direction: 'ltr'
			}, 
			document.body,
			true
		);
		diaporama1.loading = diaporama1.createElement('a', {
				className: 'highslide-loading',
				title: diaporama1.lang.loadingTitle,
				innerHTML: diaporama1.lang.loadingText,
				href: 'javascript:;'
			}, {
				position: 'absolute',
				top: '-9999px',
				opacity: diaporama1.loadingOpacity,
				zIndex: 1
			}, diaporama1.container
		);
		diaporama1.garbageBin = diaporama1.createElement('div', null, { display: 'none' }, diaporama1.container);
		diaporama1.viewport = diaporama1.createElement('div', {
				className: 'highslide-viewport highslide-viewport-size'
			}, {
				visibility: (diaporama1.safari && diaporama1.uaVersion < 525) ? 'visible' : 'hidden'
			}, diaporama1.container, 1
		);
		
		// http://www.robertpenner.com/easing/ 
		Math.linearTween = function (t, b, c, d) {
			return c*t/d + b;
		};
		Math.easeInQuad = function (t, b, c, d) {
			return c*(t/=d)*t + b;
		};
		Math.easeOutQuad = function (t, b, c, d) {
			return -c *(t/=d)*(t-2) + b;
		};
		
		diaporama1.hideSelects = diaporama1.ieLt7;
		diaporama1.hideIframes = ((window.opera && diaporama1.uaVersion < 9) || navigator.vendor == 'KDE' 
			|| (diaporama1.ie && diaporama1.uaVersion < 5.5));
	}
},
ready : function() {
	if (diaporama1.isReady) return;
	diaporama1.isReady = true;
	
	for (var i = 0; i < diaporama1.onReady.length; i++) diaporama1.onReady[i]();
},

updateAnchors : function() {
	var el, els, all = [], images = [],groups = {}, re;
		
	for (var i = 0; i < diaporama1.openerTagNames.length; i++) {
		els = document.getElementsByTagName(diaporama1.openerTagNames[i]);
		for (var j = 0; j < els.length; j++) {
			el = els[j];
			re = diaporama1.isHsAnchor(el);
			if (re) {
				diaporama1.push(all, el);
				if (re[0] == 'diaporama1.expand') diaporama1.push(images, el);
				var g = diaporama1.getParam(el, 'slideshowGroup') || 'none';
				if (!groups[g]) groups[g] = [];
				diaporama1.push(groups[g], el);
			}
		}
	}
	diaporama1.anchors = { all: all, groups: groups, images: images };
	return diaporama1.anchors;
	
},

getAnchors : function() {
	return diaporama1.anchors || diaporama1.updateAnchors();
},


close : function(el) {
	var exp = diaporama1.getExpander(el);
	if (exp) exp.close();
	return false;
}
}; // end diaporama1 object
diaporama1.fx = function( elem, options, prop ){
	this.options = options;
	this.elem = elem;
	this.prop = prop;

	if (!options.orig) options.orig = {};
};
diaporama1.fx.prototype = {
	update: function(){
		(diaporama1.fx.step[this.prop] || diaporama1.fx.step._default)(this);
		
		if (this.options.step)
			this.options.step.call(this.elem, this.now, this);

	},
	custom: function(from, to, unit){
		this.startTime = (new Date()).getTime();
		this.start = from;
		this.end = to;
		this.unit = unit;// || this.unit || "px";
		this.now = this.start;
		this.pos = this.state = 0;

		var self = this;
		function t(gotoEnd){
			return self.step(gotoEnd);
		}

		t.elem = this.elem;

		if ( t() && diaporama1.timers.push(t) == 1 ) {
			diaporama1.timerId = setInterval(function(){
				var timers = diaporama1.timers;

				for ( var i = 0; i < timers.length; i++ )
					if ( !timers[i]() )
						timers.splice(i--, 1);

				if ( !timers.length ) {
					clearInterval(diaporama1.timerId);
				}
			}, 13);
		}
	},
	step: function(gotoEnd){
		var t = (new Date()).getTime();
		if ( gotoEnd || t >= this.options.duration + this.startTime ) {
			this.now = this.end;
			this.pos = this.state = 1;
			this.update();

			this.options.curAnim[ this.prop ] = true;

			var done = true;
			for ( var i in this.options.curAnim )
				if ( this.options.curAnim[i] !== true )
					done = false;

			if ( done ) {
				if (this.options.complete) this.options.complete.call(this.elem);
			}
			return false;
		} else {
			var n = t - this.startTime;
			this.state = n / this.options.duration;
			this.pos = this.options.easing(n, 0, 1, this.options.duration);
			this.now = this.start + ((this.end - this.start) * this.pos);
			this.update();
		}
		return true;
	}

};

diaporama1.extend( diaporama1.fx, {
	step: {

		opacity: function(fx){
			diaporama1.setStyles(fx.elem, { opacity: fx.now });
		},

		_default: function(fx){
			try {
				if ( fx.elem.style && fx.elem.style[ fx.prop ] != null )
					fx.elem.style[ fx.prop ] = fx.now + fx.unit;
				else
					fx.elem[ fx.prop ] = fx.now;
			} catch (e) {}
		}
	}
});

diaporama1.Outline =  function (outlineType, onLoad) {
	this.onLoad = onLoad;
	this.outlineType = outlineType;
	var v = diaporama1.uaVersion, tr;
	
	this.hasAlphaImageLoader = diaporama1.ie && v >= 5.5 && v < 7;
	if (!outlineType) {
		if (onLoad) onLoad();
		return;
	}
	
	diaporama1.init();
	this.table = diaporama1.createElement(
		'table', { 
			cellSpacing: 0 
		}, {
			visibility: 'hidden',
			position: 'absolute',
			borderCollapse: 'collapse',
			width: 0
		},
		diaporama1.container,
		true
	);
	var tbody = diaporama1.createElement('tbody', null, null, this.table, 1);
	
	this.td = [];
	for (var i = 0; i <= 8; i++) {
		if (i % 3 == 0) tr = diaporama1.createElement('tr', null, { height: 'auto' }, tbody, true);
		this.td[i] = diaporama1.createElement('td', null, null, tr, true);
		var style = i != 4 ? { lineHeight: 0, fontSize: 0} : { position : 'relative' };
		diaporama1.setStyles(this.td[i], style);
	}
	this.td[4].className = outlineType +' highslide-outline';
	
	this.preloadGraphic(); 
};

diaporama1.Outline.prototype = {
preloadGraphic : function () {
	var src = diaporama1.graphicsDir + (diaporama1.outlinesDir || "outlines/")+ this.outlineType +".png";
				
	var appendTo = diaporama1.safari ? diaporama1.container : null;
	this.graphic = diaporama1.createElement('img', null, { position: 'absolute', 
		top: '-9999px' }, appendTo, true); // for onload trigger
	
	var pThis = this;
	this.graphic.onload = function() { pThis.onGraphicLoad(); };
	
	this.graphic.src = src;
},

onGraphicLoad : function () {
	var o = this.offset = this.graphic.width / 4,
		pos = [[0,0],[0,-4],[-2,0],[0,-8],0,[-2,-8],[0,-2],[0,-6],[-2,-2]],
		dim = { height: (2*o) +'px', width: (2*o) +'px' };
	for (var i = 0; i <= 8; i++) {
		if (pos[i]) {
			if (this.hasAlphaImageLoader) {
				var w = (i == 1 || i == 7) ? '100%' : this.graphic.width +'px';
				var div = diaporama1.createElement('div', null, { width: '100%', height: '100%', position: 'relative', overflow: 'hidden'}, this.td[i], true);
				diaporama1.createElement ('div', null, { 
						filter: "progid:DXImageTransform.Microsoft.AlphaImageLoader(sizingMethod=scale, src='"+ this.graphic.src + "')", 
						position: 'absolute',
						width: w, 
						height: this.graphic.height +'px',
						left: (pos[i][0]*o)+'px',
						top: (pos[i][1]*o)+'px'
					}, 
				div,
				true);
			} else {
				diaporama1.setStyles(this.td[i], { background: 'url('+ this.graphic.src +') '+ (pos[i][0]*o)+'px '+(pos[i][1]*o)+'px'});
			}
			
			if (window.opera && (i == 3 || i ==5)) 
				diaporama1.createElement('div', null, dim, this.td[i], true);
			
			diaporama1.setStyles (this.td[i], dim);
		}
	}
	this.graphic = null;
	if (diaporama1.pendingOutlines[this.outlineType]) diaporama1.pendingOutlines[this.outlineType].destroy();
	diaporama1.pendingOutlines[this.outlineType] = this;
	if (this.onLoad) this.onLoad();
},
	
setPosition : function (pos, offset, vis, dur, easing) {
	var exp = this.exp,
		stl = exp.wrapper.style,
		offset = offset || 0,
		pos = pos || {
			x: exp.x.pos + offset,
			y: exp.y.pos + offset,
			w: exp.x.get('wsize') - 2 * offset,
			h: exp.y.get('wsize') - 2 * offset
		};
	if (vis) this.table.style.visibility = (pos.h >= 4 * this.offset) 
		? 'visible' : 'hidden';
	diaporama1.setStyles(this.table, {
		left: (pos.x - this.offset) +'px',
		top: (pos.y - this.offset) +'px',
		width: (pos.w + 2 * this.offset) +'px'
	});
	
	pos.w -= 2 * this.offset;
	pos.h -= 2 * this.offset;
	diaporama1.setStyles (this.td[4], {
		width: pos.w >= 0 ? pos.w +'px' : 0,
		height: pos.h >= 0 ? pos.h +'px' : 0
	});
	if (this.hasAlphaImageLoader) this.td[3].style.height 
		= this.td[5].style.height = this.td[4].style.height;	
	
},
	
destroy : function(hide) {
	if (hide) this.table.style.visibility = 'hidden';
	else diaporama1.discardElement(this.table);
}
};

diaporama1.Dimension = function(exp, dim) {
	this.exp = exp;
	this.dim = dim;
	this.ucwh = dim == 'x' ? 'Width' : 'Height';
	this.wh = this.ucwh.toLowerCase();
	this.uclt = dim == 'x' ? 'Left' : 'Top';
	this.lt = this.uclt.toLowerCase();
	this.ucrb = dim == 'x' ? 'Right' : 'Bottom';
	this.rb = this.ucrb.toLowerCase();
	this.p1 = this.p2 = 0;
};
diaporama1.Dimension.prototype = {
get : function(key) {
	switch (key) {
		case 'loadingPos':
			return this.tpos + this.tb + (this.t - diaporama1.loading['offset'+ this.ucwh]) / 2;
		case 'loadingPosXfade':
			return this.pos + this.cb+ this.p1 + (this.size - diaporama1.loading['offset'+ this.ucwh]) / 2;
		case 'wsize':
			return this.size + 2 * this.cb + this.p1 + this.p2;
		case 'fitsize':
			return this.clientSize - this.marginMin - this.marginMax;
		case 'maxsize':
			return this.get('fitsize') - 2 * this.cb - this.p1 - this.p2 ;
		case 'opos':
			return this.pos - (this.exp.outline ? this.exp.outline.offset : 0);
		case 'osize':
			return this.get('wsize') + (this.exp.outline ? 2*this.exp.outline.offset : 0);
		case 'imgPad':
			return this.imgSize ? Math.round((this.size - this.imgSize) / 2) : 0;
		
	}
},
calcBorders: function() {
	// correct for borders
	this.cb = (this.exp.content['offset'+ this.ucwh] - this.t) / 2;
	
	this.marginMax = diaporama1['margin'+ this.ucrb];
},
calcThumb: function() {
	this.t = this.exp.el[this.wh] ? parseInt(this.exp.el[this.wh]) : 
		this.exp.el['offset'+ this.ucwh];
	this.tpos = this.exp.tpos[this.dim];
	this.tb = (this.exp.el['offset'+ this.ucwh] - this.t) / 2;
	if (this.tpos == 0 || this.tpos == -1) {
		this.tpos = (diaporama1.page[this.wh] / 2) + diaporama1.page['scroll'+ this.uclt];		
	};
},
calcExpanded: function() {
	var exp = this.exp;
	this.justify = 'auto';
	
	// get alignment
	if (exp.align == 'center') this.justify = 'center';
	else if (new RegExp(this.lt).test(exp.anchor)) this.justify = null;
	else if (new RegExp(this.rb).test(exp.anchor)) this.justify = 'max';
	
	
	// size and position
	this.pos = this.tpos - this.cb + this.tb;
	
	if (this.dim == 'x')
		exp.maxWidth = Math.min(exp.maxWidth || this.full, exp.maxHeight * this.full / exp.y.full); 
		
	this.size = Math.min(this.full, exp['max'+ this.ucwh] || this.full);
	this.minSize = exp.allowSizeReduction ? 
		Math.min(exp['min'+ this.ucwh], this.full) :this.full;
	if (exp.isImage && exp.useBox)	{
		this.size = exp[this.wh];
		this.imgSize = this.full;
	}
	if (this.dim == 'x' && diaporama1.padToMinWidth) this.minSize = exp.minWidth;
	this.target = exp['target'+ this.dim.toUpperCase()];
	this.marginMin = diaporama1['margin'+ this.uclt];
	this.scroll = diaporama1.page['scroll'+ this.uclt];
	this.clientSize = diaporama1.page[this.wh];
},
setSize: function(i) {
	var exp = this.exp;
	if (exp.isImage && (exp.useBox || diaporama1.padToMinWidth)) {
		this.imgSize = i;
		this.size = Math.max(this.size, this.imgSize);
		exp.content.style[this.lt] = this.get('imgPad')+'px';
	} else
	this.size = i;

	exp.content.style[this.wh] = i +'px';
	exp.wrapper.style[this.wh] = this.get('wsize') +'px';
	if (exp.outline) exp.outline.setPosition();
	if (this.dim == 'x' && exp.overlayBox) exp.sizeOverlayBox(true);
	if (this.dim == 'x' && exp.slideshow && exp.isImage) {
		if (i == this.full) exp.slideshow.disable('full-expand');
		else exp.slideshow.enable('full-expand');
	}
},
setPos: function(i) {
	this.pos = i;
	this.exp.wrapper.style[this.lt] = i +'px';	
	
	if (this.exp.outline) this.exp.outline.setPosition();
	
}
};

diaporama1.Expander = function(a, params, custom, contentType) {
	if (document.readyState && diaporama1.ie && !diaporama1.isReady) {
		diaporama1.addEventListener(document, 'ready', function() {
			new diaporama1.Expander(a, params, custom, contentType);
		});
		return;
	} 
	this.a = a;
	this.custom = custom;
	this.contentType = contentType || 'image';
	this.isImage = !this.isHtml;
	
	diaporama1.continuePreloading = false;
	this.overlays = [];
	this.last = diaporama1.last;
	diaporama1.last = null;
	diaporama1.init();
	var key = this.key = diaporama1.expanders.length;
	// override inline parameters
	for (var i = 0; i < diaporama1.overrides.length; i++) {
		var name = diaporama1.overrides[i];
		this[name] = params && typeof params[name] != 'undefined' ?
			params[name] : diaporama1[name];
	}
	if (!this.src) this.src = a.href;
	
	// get thumb
	var el = (params && params.thumbnailId) ? diaporama1.$(params.thumbnailId) : a;
	el = this.thumb = el.getElementsByTagName('img')[0] || el;
	this.thumbsUserSetId = el.id || a.id;
	
	// check if already open
	for (var i = 0; i < diaporama1.expanders.length; i++) {
		if (diaporama1.expanders[i] && diaporama1.expanders[i].a == a 
			&& !(this.last && this.transitions[1] == 'crossfade')) {
			diaporama1.expanders[i].focus();
			return false;
		}
	}	

	// cancel other
	if (!diaporama1.allowSimultaneousLoading) for (var i = 0; i < diaporama1.expanders.length; i++) {
		if (diaporama1.expanders[i] && diaporama1.expanders[i].thumb != el && !diaporama1.expanders[i].onLoadStarted) {
			diaporama1.expanders[i].cancelLoading();
		}
	}
	diaporama1.expanders[key] = this;
	if (!diaporama1.allowMultipleInstances && !diaporama1.upcoming) {
		if (diaporama1.expanders[key-1]) diaporama1.expanders[key-1].close();
		if (typeof diaporama1.focusKey != 'undefined' && diaporama1.expanders[diaporama1.focusKey])
			diaporama1.expanders[diaporama1.focusKey].close();
	}
	
	// initiate metrics
	this.el = el;
	this.tpos = diaporama1.getPosition(el);
	diaporama1.getPageSize();
	var x = this.x = new diaporama1.Dimension(this, 'x');
	x.calcThumb();
	var y = this.y = new diaporama1.Dimension(this, 'y');
	y.calcThumb();
	this.wrapper = diaporama1.createElement(
		'div', {
			id: 'highslide-wrapper-'+ this.key,
			className: 'highslide-wrapper '+ this.wrapperClassName
		}, {
			visibility: 'hidden',
			position: 'absolute',
			zIndex: diaporama1.zIndexCounter += 2
		}, null, true );
	
	this.wrapper.onmouseover = this.wrapper.onmouseout = diaporama1.wrapperMouseHandler;
	if (this.contentType == 'image' && this.outlineWhileAnimating == 2)
		this.outlineWhileAnimating = 0;
	
	// get the outline
	if (!this.outlineType 
		|| (this.last && this.isImage && this.transitions[1] == 'crossfade')) {
		this[this.contentType +'Create']();
	
	} else if (diaporama1.pendingOutlines[this.outlineType]) {
		this.connectOutline();
		this[this.contentType +'Create']();
	
	} else {
		this.showLoading();
		var exp = this;
		new diaporama1.Outline(this.outlineType, 
			function () {
				exp.connectOutline();
				exp[exp.contentType +'Create']();
			} 
		);
	}
	return true;
};

diaporama1.Expander.prototype = {
error : function(e) {
	// alert ('Line '+ e.lineNumber +': '+ e.message);
	window.location.href = this.src;
},

connectOutline : function() {
	var outline = this.outline = diaporama1.pendingOutlines[this.outlineType];
	outline.exp = this;
	outline.table.style.zIndex = this.wrapper.style.zIndex - 1;
	diaporama1.pendingOutlines[this.outlineType] = null;
},

showLoading : function() {
	if (this.onLoadStarted || this.loading) return;
	
	this.loading = diaporama1.loading;
	var exp = this;
	this.loading.onclick = function() {
		exp.cancelLoading();
	};
	var exp = this, 
		l = this.x.get('loadingPos') +'px',
		t = this.y.get('loadingPos') +'px';
	if (!tgt && this.last && this.transitions[1] == 'crossfade') 
		var tgt = this.last; 
	if (tgt) {
		l = tgt.x.get('loadingPosXfade') +'px';
		t = tgt.y.get('loadingPosXfade') +'px';
		this.loading.style.zIndex = diaporama1.zIndexCounter++;
	}
	setTimeout(function () { 
		if (exp.loading) diaporama1.setStyles(exp.loading, { left: l, top: t, zIndex: diaporama1.zIndexCounter++ })}
	, 100);
},

imageCreate : function() {
	var exp = this;
	
	var img = document.createElement('img');
    this.content = img;
    img.onload = function () {
    	if (diaporama1.expanders[exp.key]) exp.contentLoaded(); 
	};
    if (diaporama1.blockRightClick) img.oncontextmenu = function() { return false; };
    img.className = 'highslide-image';
    diaporama1.setStyles(img, {
    	visibility: 'hidden',
    	display: 'block',
    	position: 'absolute',
		maxWidth: '9999px',
		zIndex: 3
	});
    img.title = diaporama1.lang.restoreTitle;
    if (diaporama1.safari) diaporama1.container.appendChild(img);
    if (diaporama1.ie && diaporama1.flushImgSize) img.src = null;
	img.src = this.src;
	
	this.showLoading();
},

contentLoaded : function() {
	try {	
		if (!this.content) return;
		this.content.onload = null;
		if (this.onLoadStarted) return;
		else this.onLoadStarted = true;
		
		var x = this.x, y = this.y;
		
		if (this.loading) {
			diaporama1.setStyles(this.loading, { top: '-9999px' });
			this.loading = null;
		}	
			x.full = this.content.width;
			y.full = this.content.height;
			
			diaporama1.setStyles(this.content, {
				width: x.t +'px',
				height: y.t +'px'
			});
			this.wrapper.appendChild(this.content);
			diaporama1.container.appendChild(this.wrapper);
		
		x.calcBorders();
		y.calcBorders();
		
		diaporama1.setStyles (this.wrapper, {
			left: (x.tpos + x.tb - x.cb) +'px',
			top: (y.tpos + x.tb - y.cb) +'px'
		});
		
		
		this.initSlideshow();
		this.getOverlays();
		
		var ratio = x.full / y.full;
		x.calcExpanded();
		this.justify(x);
		
		y.calcExpanded();
		this.justify(y);
		if (this.overlayBox) this.sizeOverlayBox(0, 1);
		
		if (this.allowSizeReduction) {
				this.correctRatio(ratio);
			var ss = this.slideshow;			
			if (ss && this.last && ss.controls && ss.fixedControls) {
				var pos = ss.overlayOptions.position || '', p;
				for (var dim in diaporama1.oPos) for (var i = 0; i < 5; i++) {
					p = this[dim];
					if (pos.match(diaporama1.oPos[dim][i])) {
						p.pos = this.last[dim].pos 
							+ (this.last[dim].p1 - p.p1)
							+ (this.last[dim].size - p.size) * [0, 0, .5, 1, 1][i];
						if (ss.fixedControls == 'fit') {
							if (p.pos + p.size + p.p1 + p.p2 > p.scroll + p.clientSize - p.marginMax)
								p.pos = p.scroll + p.clientSize - p.size - p.marginMin - p.marginMax - p.p1 - p.p2;
							if (p.pos < p.scroll + p.marginMin) p.pos = p.scroll + p.marginMin; 
						} 
					}
				}
			}
			if (this.isImage && this.x.full > (this.x.imgSize || this.x.size)) {
				this.createFullExpand();
				if (this.overlays.length == 1) this.sizeOverlayBox();
			}
		}
		this.show();
		
	} catch (e) {
		this.error(e);
	}
},

justify : function (p, moveOnly) {
	var tgtArr, tgt = p.target, dim = p == this.x ? 'x' : 'y';
	
	if (tgt && tgt.match(/ /)) {
		tgtArr = tgt.split(' ');
		tgt = tgtArr[0];
	}
	if (tgt && diaporama1.$(tgt)) {
		p.pos = diaporama1.getPosition(diaporama1.$(tgt))[dim];
		if (tgtArr && tgtArr[1] && tgtArr[1].match(/^[-]?[0-9]+px$/)) 
			p.pos += parseInt(tgtArr[1]);
		if (p.size < p.minSize) p.size = p.minSize;
		
	} else if (p.justify == 'auto' || p.justify == 'center') {
	
		var hasMovedMin = false;
		
		var allowReduce = p.exp.allowSizeReduction;
		if (p.justify == 'center')
			p.pos = Math.round(p.scroll + (p.clientSize + p.marginMin - p.marginMax - p.get('wsize')) / 2);
		else
			p.pos = Math.round(p.pos - ((p.get('wsize') - p.t) / 2));
		if (p.pos < p.scroll + p.marginMin) {
			p.pos = p.scroll + p.marginMin;
			hasMovedMin = true;		
		}
		if (!moveOnly && p.size < p.minSize) {
			p.size = p.minSize;
			allowReduce = false;
		}
		if (p.pos + p.get('wsize') > p.scroll + p.clientSize - p.marginMax) {
			if (!moveOnly && hasMovedMin && allowReduce) {
				p.size = Math.min(p.size, p.get(dim == 'y' ? 'fitsize' : 'maxsize'));
			} else if (p.get('wsize') < p.get('fitsize')) {
				p.pos = p.scroll + p.clientSize - p.marginMax - p.get('wsize');
			} else { // image larger than viewport
				p.pos = p.scroll + p.marginMin;
				if (!moveOnly && allowReduce) p.size = p.get(dim == 'y' ? 'fitsize' : 'maxsize');
			}			
		}
		
		if (!moveOnly && p.size < p.minSize) {
			p.size = p.minSize;
			allowReduce = false;
		}
		
	
	} else if (p.justify == 'max') {
		p.pos = Math.floor(p.pos - p.size + p.t);
	}
	
		
	if (p.pos < p.marginMin) {
		var tmpMin = p.pos;
		p.pos = p.marginMin; 
		
		if (allowReduce && !moveOnly) p.size = p.size - (p.pos - tmpMin);
		
	}
},

correctRatio : function(ratio) {
	var x = this.x, 
		y = this.y,
		changed = false,
		xSize = Math.min(x.full, x.size),
		ySize = Math.min(y.full, y.size),
		useBox = (this.useBox || diaporama1.padToMinWidth);
	
	if (xSize / ySize > ratio) { // width greater
		xSize = ySize * ratio;
		if (xSize < x.minSize) { // below minWidth
			xSize = x.minSize;
			ySize = xSize / ratio;
		}
		changed = true;
	
	} else if (xSize / ySize < ratio) { // height greater
		ySize = xSize / ratio;
		changed = true;
	}
	
	if (diaporama1.padToMinWidth && x.full < x.minSize) {
		x.imgSize = x.full;
		y.size = y.imgSize = y.full;
	} else if (this.useBox) {
		x.imgSize = xSize;
		y.imgSize = ySize;
	} else {
		x.size = xSize;
		y.size = ySize;
	}
	changed = this.fitOverlayBox(useBox ? null : ratio, changed);
	if (useBox && y.size < y.imgSize) {
		y.imgSize = y.size;
		x.imgSize = y.size * ratio;
	}
	if (changed || useBox) {
		x.pos = x.tpos - x.cb + x.tb;
		x.minSize = x.size;
		this.justify(x, true);
	
		y.pos = y.tpos - y.cb + y.tb;
		y.minSize = y.size;
		this.justify(y, true);
		if (this.overlayBox) this.sizeOverlayBox();
	}
},
fitOverlayBox : function(ratio, changed) {
	var x = this.x, y = this.y;
	if (this.overlayBox) {
		while (y.size > this.minHeight && x.size > this.minWidth 
				&&  y.get('wsize') > y.get('fitsize')) {
			y.size -= 10;
			if (ratio) x.size = y.size * ratio;
			this.sizeOverlayBox(0, 1);
			changed = true;
		}
	}
	return changed;
},

show : function () {
	var x = this.x, y = this.y;
	this.doShowHide('hidden');
	if (this.slideshow && this.slideshow.thumbstrip) this.slideshow.thumbstrip.selectThumb();
	
	// Apply size change
	this.changeSize(
		1, {
			wrapper: {
				width : x.get('wsize'),
				height : y.get('wsize'),
				left: x.pos,
				top: y.pos
			},
			content: {
				left: x.p1 + x.get('imgPad'),
				top: y.p1 + y.get('imgPad'),
				width:x.imgSize ||x.size,
				height:y.imgSize ||y.size
			}
		},
		diaporama1.expandDuration
	);
},

changeSize : function(up, to, dur) {
	// transition
	var trans = this.transitions,
	other = up ? (this.last ? this.last.a : null) : diaporama1.upcoming,
	t = (trans[1] && other 
			&& diaporama1.getParam(other, 'transitions')[1] == trans[1]) ?
		trans[1] : trans[0];
		
	if (this[t] && t != 'expand') {
		this[t](up, to);
		return;
	}
	
	if (this.outline && !this.outlineWhileAnimating) {
		if (up) this.outline.setPosition();
		else this.outline.destroy();
	}
	
	
	if (!up) this.destroyOverlays();
	
	var exp = this,
		x = exp.x,
		y = exp.y,
		easing = this.easing;
	if (!up) easing = this.easingClose || easing;
	var after = up ?
		function() {
				
			if (exp.outline) exp.outline.table.style.visibility = "visible";
			setTimeout(function() {
				exp.afterExpand();
			}, 50);
		} :
		function() {
			exp.afterClose();
		};
	if (up) diaporama1.setStyles( this.wrapper, {
		width: x.t +'px',
		height: y.t +'px'
	});
	if (this.fadeInOut) {
		diaporama1.setStyles(this.wrapper, { opacity: up ? 0 : 1 });
		diaporama1.extend(to.wrapper, { opacity: up });
	}
	diaporama1.animate( this.wrapper, to.wrapper, {
		duration: dur,
		easing: easing,
		step: function(val, args) {
			if (exp.outline && exp.outlineWhileAnimating && args.prop == 'top') {
				var fac = up ? args.pos : 1 - args.pos;
				var pos = {
					w: x.t + (x.get('wsize') - x.t) * fac,
					h: y.t + (y.get('wsize') - y.t) * fac,
					x: x.tpos + (x.pos - x.tpos) * fac,
					y: y.tpos + (y.pos - y.tpos) * fac
				};
				exp.outline.setPosition(pos, 0, 1);				
			}
		}
	});
	diaporama1.animate( this.content, to.content, dur, easing, after);
	if (up) {
		this.wrapper.style.visibility = 'visible';
		this.content.style.visibility = 'visible';
		this.a.className += ' highslide-active-anchor';
	}
},



fade : function(up, to) {
	this.outlineWhileAnimating = false;
	var exp = this,	t = up ? diaporama1.expandDuration : 0;
	
	if (up) {
		diaporama1.animate(this.wrapper, to.wrapper, 0);
		diaporama1.setStyles(this.wrapper, { opacity: 0, visibility: 'visible' });
		diaporama1.animate(this.content, to.content, 0);
		this.content.style.visibility = 'visible';

		diaporama1.animate(this.wrapper, { opacity: 1 }, t, null, 
			function() { exp.afterExpand(); });
	}
	
	if (this.outline) {
		this.outline.table.style.zIndex = this.wrapper.style.zIndex;
		var dir = up || -1, 
			offset = this.outline.offset,
			startOff = up ? 3 : offset,
			endOff = up? offset : 3;
		for (var i = startOff; dir * i <= dir * endOff; i += dir, t += 25) {
			(function() {
				var o = up ? endOff - i : startOff - i;
				setTimeout(function() {
					exp.outline.setPosition(0, o, 1);
				}, t);
			})();
		}
	}
	
	
	if (up) {}//setTimeout(function() { exp.afterExpand(); }, t+50);
	else {
		setTimeout( function() {
			if (exp.outline) exp.outline.destroy(exp.preserveContent);
			
			exp.destroyOverlays();
	
			diaporama1.animate( exp.wrapper, { opacity: 0 }, diaporama1.restoreDuration, null, function(){
				exp.afterClose();
			});
		}, t);		
	}
},
crossfade : function (up, to, from) {
	if (!up) return;
	var exp = this, 
		last = this.last,
		x = this.x,
		y = this.y,
		lastX = last.x,
		lastY = last.y,
		wrapper = this.wrapper,
		content = this.content,
		overlayBox = this.overlayBox;
	diaporama1.removeEventListener(document, 'mousemove', diaporama1.dragHandler);
	
	diaporama1.setStyles(content, { 
		width: (x.imgSize || x.size) +'px', 
		height: (y.imgSize || y.size) +'px'		
	});
	if (overlayBox) overlayBox.style.overflow = 'visible';
	this.outline = last.outline;
	if (this.outline) this.outline.exp = exp;
	last.outline = null;
	var fadeBox = diaporama1.createElement('div', {
			className: 'highslide-image'
		}, { 
			position: 'absolute', 
			zIndex: 4,
			overflow: 'hidden',
			display: 'none'
		}
	);
	var names = { oldImg: last, newImg: this };
	for (var n in names) { 	
		this[n] = names[n].content.cloneNode(1);
		diaporama1.setStyles(this[n], {
			position: 'absolute',
			border: 0,
			visibility: 'visible'
		});
		fadeBox.appendChild(this[n]);
	}
	wrapper.appendChild(fadeBox);
	if (overlayBox) {
		overlayBox.className = '';
		wrapper.appendChild(overlayBox);
	}
	fadeBox.style.display = '';
	last.content.style.display = 'none';
	
	
	if (diaporama1.safari) {
		var match = navigator.userAgent.match(/Safari\/([0-9]{3})/);
		if (match && parseInt(match[1]) < 525) this.wrapper.style.visibility = 'visible';
	}
	diaporama1.animate(wrapper, {
		width: x.size
	}, {
		duration: diaporama1.transitionDuration, 
		step: function(val, args) {
			var pos = args.pos,
				invPos = 1 - pos;
			var prop,
				size = {}, 
				props = ['pos', 'size', 'p1', 'p2'];
			for (var n in props) {
				prop = props[n];
				size['x'+ prop] = invPos * lastX[prop] + pos * x[prop];
				size['y'+ prop] = invPos * lastY[prop] + pos * y[prop];
				size.ximgSize = invPos * (lastX.imgSize || lastX.size) + pos * (x.imgSize || x.size);
				size.ximgPad = invPos * lastX.get('imgPad') + pos * x.get('imgPad');
				size.yimgSize = invPos * (lastY.imgSize || lastY.size) + pos * (y.imgSize || y.size);
				size.yimgPad = invPos * lastY.get('imgPad') + pos * y.get('imgPad');
			}
			if (exp.outline) exp.outline.setPosition({ 
				x: size.xpos, 
				y: size.ypos, 
				w: size.xsize + size.xp1 + size.xp2 + 2 * x.cb, 
				h: size.ysize + size.yp1 + size.yp2 + 2 * y.cb
			});
			last.wrapper.style.clip = 'rect('
				+ (size.ypos - lastY.pos)+'px, '
				+ (size.xsize + size.xp1 + size.xp2 + size.xpos + 2 * lastX.cb - lastX.pos) +'px, '
				+ (size.ysize + size.yp1 + size.yp2 + size.ypos + 2 * lastY.cb - lastY.pos) +'px, '
				+ (size.xpos - lastX.pos)+'px)';
				
			diaporama1.setStyles(content, {
				top: (size.yp1 + y.get('imgPad')) +'px',
				left: (size.xp1 + x.get('imgPad')) +'px',
				marginTop: (y.pos - size.ypos) +'px',
				marginLeft: (x.pos - size.xpos) +'px'
			});
			
			diaporama1.setStyles(wrapper, {
				top: size.ypos +'px',
				left: size.xpos +'px',
				width: (size.xp1 + size.xp2 + size.xsize + 2 * x.cb)+ 'px',
				height: (size.yp1 + size.yp2 + size.ysize + 2 * y.cb) + 'px'
			});
			diaporama1.setStyles(fadeBox, {
				width: (size.ximgSize || size.xsize) + 'px',
				height: (size.yimgSize || size.ysize) +'px',
				left: (size.xp1 + size.ximgPad)  +'px',
				top: (size.yp1 + size.yimgPad) +'px',
				visibility: 'visible'
			});
			
			diaporama1.setStyles(exp.oldImg, {
				top: (lastY.pos - size.ypos + lastY.p1 - size.yp1 + lastY.get('imgPad') - size.yimgPad)+'px',
				left: (lastX.pos - size.xpos + lastX.p1 - size.xp1 + lastX.get('imgPad') - size.ximgPad)+'px'
			});		
			
			diaporama1.setStyles(exp.newImg, {
				opacity: pos,
				top: (y.pos - size.ypos + y.p1 - size.yp1 + y.get('imgPad') - size.yimgPad) +'px',
				left: (x.pos - size.xpos + x.p1 - size.xp1 + x.get('imgPad') - size.ximgPad) +'px'
			});
			if (overlayBox) diaporama1.setStyles(overlayBox, {
				width: size.xsize + 'px',
				height: size.ysize +'px',
				left: (size.xp1 + x.cb)  +'px',
				top: (size.yp1 + y.cb) +'px'
			});
		},
		complete: function () {
			wrapper.style.visibility = content.style.visibility = 'visible';
			content.style.display = 'block';
			fadeBox.style.display = 'none';
			exp.a.className += ' highslide-active-anchor';
			exp.afterExpand();
			last.afterClose();
			exp.last = null;
		}
		
	});
},
reuseOverlay : function(o, el) {
	if (!this.last) return false;
	for (var i = 0; i < this.last.overlays.length; i++) {
		var oDiv = diaporama1.$('hsId'+ this.last.overlays[i]);
		if (oDiv && oDiv.hsId == o.hsId) {
			this.genOverlayBox();
			oDiv.reuse = this.key;
			diaporama1.push(this.overlays, this.last.overlays[i]);
			return true;
		}
	}
	return false;
},


afterExpand : function() {
	this.isExpanded = true;	
	this.focus();
	if (this.dimmingOpacity) diaporama1.dim(this);
	if (diaporama1.upcoming && diaporama1.upcoming == this.a) diaporama1.upcoming = null;
	this.prepareNextOutline();
	var p = diaporama1.page, mX = diaporama1.mouse.x + p.scrollLeft, mY = diaporama1.mouse.y + p.scrollTop;
	this.mouseIsOver = this.x.pos < mX && mX < this.x.pos + this.x.get('wsize')
		&& this.y.pos < mY && mY < this.y.pos + this.y.get('wsize');	
	if (this.overlayBox) this.showOverlays();
	
},


prepareNextOutline : function() {
	var key = this.key;
	var outlineType = this.outlineType;
	new diaporama1.Outline(outlineType, 
		function () { try { diaporama1.expanders[key].preloadNext(); } catch (e) {} });
},


preloadNext : function() {
	var next = this.getAdjacentAnchor(1);
	if (next && next.onclick.toString().match(/diaporama1\.expand/)) 
		var img = diaporama1.createElement('img', { src: diaporama1.getSrc(next) });
},


getAdjacentAnchor : function(op) {
	var current = this.getAnchorIndex(), as = diaporama1.anchors.groups[this.slideshowGroup || 'none'];
	
	/*< ? if ($cfg->slideshow) : ?>s*/
	if (!as[current + op] && this.slideshow && this.slideshow.repeat) {
		if (op == 1) return as[0];
		else if (op == -1) return as[as.length-1];
	}
	/*< ? endif ?>s*/
	return as[current + op] || null;
},

getAnchorIndex : function() {
	var arr = diaporama1.getAnchors().groups[this.slideshowGroup || 'none'];
	if (arr) for (var i = 0; i < arr.length; i++) {
		if (arr[i] == this.a) return i; 
	}
	return null;
},


getNumber : function() {
	if (this[this.numberPosition]) {
		var arr = diaporama1.anchors.groups[this.slideshowGroup || 'none'];
		if (arr) {
			var s = diaporama1.lang.number.replace('%1', this.getAnchorIndex() + 1).replace('%2', arr.length);
			this[this.numberPosition].innerHTML = 
				'<div class="highslide-number">'+ s +'</div>'+ this[this.numberPosition].innerHTML;
		}
	}
},
initSlideshow : function() {
	if (!this.last) {
		for (var i = 0; i < diaporama1.slideshows.length; i++) {
			var ss = diaporama1.slideshows[i], sg = ss.slideshowGroup;
			if (typeof sg == 'undefined' || sg === null || sg === this.slideshowGroup) 
				this.slideshow = new diaporama1.Slideshow(this.key, ss);
		} 
	} else {
		this.slideshow = this.last.slideshow;
	}
	var ss = this.slideshow;
	if (!ss) return;
	var key = ss.expKey = this.key;
	
	ss.checkFirstAndLast();
	ss.disable('full-expand');
	if (ss.controls) {
		var o = ss.overlayOptions || {};
		o.overlayId = ss.controls;
		o.hsId = 'controls';		
		this.createOverlay(o);
	}
	if (ss.thumbstrip) ss.thumbstrip.add(this);
	if (!this.last && this.autoplay) ss.play(true);
	if (ss.autoplay) {
		ss.autoplay = setTimeout(function() {
			diaporama1.next(key);
		}, (ss.interval || 500));
	}
},

cancelLoading : function() {
	diaporama1.discardElement (this.wrapper);
	diaporama1.expanders[this.key] = null;
	if (diaporama1.upcoming == this.a) diaporama1.upcoming = null;
	diaporama1.undim(this.key);
	if (this.loading) diaporama1.loading.style.left = '-9999px';
},

writeCredits : function () {
	if (this.credits) return;
	this.credits = diaporama1.createElement('a', {
		href: diaporama1.creditsHref,
		target: diaporama1.creditsTarget,
		className: 'highslide-credits',
		innerHTML: diaporama1.lang.creditsText,
		title: diaporama1.lang.creditsTitle
	});
	this.createOverlay({ 
		overlayId: this.credits, 
		position: this.creditsPosition || 'top left', 
		hsId: 'credits' 
	});
},

getInline : function(types, addOverlay) {
	for (var i = 0; i < types.length; i++) {
		var type = types[i], s = null;
		if (!this[type +'Id'] && this.thumbsUserSetId)  
			this[type +'Id'] = type +'-for-'+ this.thumbsUserSetId;
		if (this[type +'Id']) this[type] = diaporama1.getNode(this[type +'Id']);
		if (!this[type] && !this[type +'Text'] && this[type +'Eval']) try {
			s = eval(this[type +'Eval']);
		} catch (e) {}
		if (!this[type] && this[type +'Text']) {
			s = this[type +'Text'];
		}
		if (!this[type] && !s) {
			this[type] = diaporama1.getNode(this.a['_'+ type + 'Id']);
			if (!this[type]) {
				var next = this.a.nextSibling;
				while (next && !diaporama1.isHsAnchor(next)) {
					if ((new RegExp('highslide-'+ type)).test(next.className || null)) {
						if (!next.id) this.a['_'+ type + 'Id'] = next.id = 'hsId'+ diaporama1.idCounter++;
						this[type] = diaporama1.getNode(next.id);
						break;
					}
					next = next.nextSibling;
				}
			}
		}
		if (!this[type] && !s && this.numberPosition == type) s = '\n';
		
		if (!this[type] && s) this[type] = diaporama1.createElement('div', 
				{ className: 'highslide-'+ type, innerHTML: s } );
		
		if (addOverlay && this[type]) {
			var o = { position: (type == 'heading') ? 'above' : 'below' };
			for (var x in this[type+'Overlay']) o[x] = this[type+'Overlay'][x];
			o.overlayId = this[type];
			this.createOverlay(o);
		}
	}
},


// on end move and resize
doShowHide : function(visibility) {
	if (diaporama1.hideSelects) this.showHideElements('SELECT', visibility);
	if (diaporama1.hideIframes) this.showHideElements('IFRAME', visibility);
	if (diaporama1.geckoMac) this.showHideElements('*', visibility);
},
showHideElements : function (tagName, visibility) {
	var els = document.getElementsByTagName(tagName);
	var prop = tagName == '*' ? 'overflow' : 'visibility';
	for (var i = 0; i < els.length; i++) {
		if (prop == 'visibility' || (document.defaultView.getComputedStyle(
				els[i], "").getPropertyValue('overflow') == 'auto'
				|| els[i].getAttribute('hidden-by') != null)) {
			var hiddenBy = els[i].getAttribute('hidden-by');
			if (visibility == 'visible' && hiddenBy) {
				hiddenBy = hiddenBy.replace('['+ this.key +']', '');
				els[i].setAttribute('hidden-by', hiddenBy);
				if (!hiddenBy) els[i].style[prop] = els[i].origProp;
			} else if (visibility == 'hidden') { // hide if behind
				var elPos = diaporama1.getPosition(els[i]);
				elPos.w = els[i].offsetWidth;
				elPos.h = els[i].offsetHeight;
				if (!this.dimmingOpacity) { // hide all if dimming
				
					var clearsX = (elPos.x + elPos.w < this.x.get('opos') 
						|| elPos.x > this.x.get('opos') + this.x.get('osize'));
					var clearsY = (elPos.y + elPos.h < this.y.get('opos') 
						|| elPos.y > this.y.get('opos') + this.y.get('osize'));
				}
				var wrapperKey = diaporama1.getWrapperKey(els[i]);
				if (!clearsX && !clearsY && wrapperKey != this.key) { // element falls behind image
					if (!hiddenBy) {
						els[i].setAttribute('hidden-by', '['+ this.key +']');
						els[i].origProp = els[i].style[prop];
						els[i].style[prop] = 'hidden';
						
					} else if (hiddenBy.indexOf('['+ this.key +']') == -1) {
						els[i].setAttribute('hidden-by', hiddenBy + '['+ this.key +']');
					}
				} else if ((hiddenBy == '['+ this.key +']' || diaporama1.focusKey == wrapperKey)
						&& wrapperKey != this.key) { // on move
					els[i].setAttribute('hidden-by', '');
					els[i].style[prop] = els[i].origProp || '';
				} else if (hiddenBy && hiddenBy.indexOf('['+ this.key +']') > -1) {
					els[i].setAttribute('hidden-by', hiddenBy.replace('['+ this.key +']', ''));
				}
						
			}
		}
	}
},

focus : function() {
	this.wrapper.style.zIndex = diaporama1.zIndexCounter += 2;
	// blur others
	for (var i = 0; i < diaporama1.expanders.length; i++) {
		if (diaporama1.expanders[i] && i == diaporama1.focusKey) {
			var blurExp = diaporama1.expanders[i];
			blurExp.content.className += ' highslide-'+ blurExp.contentType +'-blur';
				blurExp.content.style.cursor = diaporama1.ie ? 'hand' : 'pointer';
				blurExp.content.title = diaporama1.lang.focusTitle;
		}
	}
	
	// focus this
	if (this.outline) this.outline.table.style.zIndex 
		= this.wrapper.style.zIndex - 1;
	this.content.className = 'highslide-'+ this.contentType;
		this.content.title = diaporama1.lang.restoreTitle;
		
		if (diaporama1.restoreCursor) {
			diaporama1.styleRestoreCursor = window.opera ? 'pointer' : 'url('+ diaporama1.graphicsDir + diaporama1.restoreCursor +'), pointer';
			if (diaporama1.ie && diaporama1.uaVersion < 6) diaporama1.styleRestoreCursor = 'hand';
			this.content.style.cursor = diaporama1.styleRestoreCursor;
		}
		
	diaporama1.focusKey = this.key;	
	diaporama1.addEventListener(document, window.opera ? 'keypress' : 'keydown', diaporama1.keyHandler);	
},
moveTo: function(x, y) {
	this.x.setPos(x);
	this.y.setPos(y);
},
resize : function (e) {
	var w, h, r = e.width / e.height;
	w = Math.max(e.width + e.dX, Math.min(this.minWidth, this.x.full));
	if (this.isImage && Math.abs(w - this.x.full) < 12) w = this.x.full;
	h = w / r;
	if (h < Math.min(this.minHeight, this.y.full)) {
		h = Math.min(this.minHeight, this.y.full);
		if (this.isImage) w = h * r;
	}
	this.resizeTo(w, h);
},
resizeTo: function(w, h) {
	this.y.setSize(h);
	this.x.setSize(w);
	this.wrapper.style.height = this.y.get('wsize') +'px';
},

close : function() {
	if (this.isClosing || !this.isExpanded) return;
	if (this.transitions[1] == 'crossfade' && diaporama1.upcoming) {
		diaporama1.getExpander(diaporama1.upcoming).cancelLoading();
		diaporama1.upcoming = null;
	}
	this.isClosing = true;
	if (this.slideshow && !diaporama1.upcoming) this.slideshow.pause();
	
	diaporama1.removeEventListener(document, window.opera ? 'keypress' : 'keydown', diaporama1.keyHandler);
	
	try {
		this.content.style.cursor = 'default';
		this.changeSize(
			0, {
				wrapper: {
					width : this.x.t,
					height : this.y.t,
					left: this.x.tpos - this.x.cb + this.x.tb,
					top: this.y.tpos - this.y.cb + this.y.tb
				},
				content: {
					left: 0,
					top: 0,
					width: this.x.t,
					height: this.y.t
				}
			}, diaporama1.restoreDuration
		);
	} catch (e) { this.afterClose(); }
},

createOverlay : function (o) {
	var el = o.overlayId, 
		relToVP = (o.relativeTo == 'viewport' && !/panel$/.test(o.position));
	if (typeof el == 'string') el = diaporama1.getNode(el);
	if (o.html) el = diaporama1.createElement('div', { innerHTML: o.html });
	if (!el || typeof el == 'string') return;
	el.style.display = 'block';
	o.hsId = o.hsId || o.overlayId; 
	if (this.transitions[1] == 'crossfade' && this.reuseOverlay(o, el)) return;
	this.genOverlayBox();
	var width = o.width && /^[0-9]+(px|%)$/.test(o.width) ? o.width : 'auto';
	if (/^(left|right)panel$/.test(o.position) && !/^[0-9]+px$/.test(o.width)) width = '200px';
	var overlay = diaporama1.createElement(
		'div', {
			id: 'hsId'+ diaporama1.idCounter++,
			hsId: o.hsId
		}, {
			position: 'absolute',
			visibility: 'hidden',
			width: width,
			direction: diaporama1.lang.cssDirection || '',
			opacity: 0
		},
		relToVP ? diaporama1.viewport :this.overlayBox,
		true
	);
	if (relToVP) overlay.hsKey = this.key;
	
	overlay.appendChild(el);
	diaporama1.extend(overlay, {
		opacity: 1,
		offsetX: 0,
		offsetY: 0,
		dur: (o.fade === 0 || o.fade === false || (o.fade == 2 && diaporama1.ie)) ? 0 : 250
	});
	diaporama1.extend(overlay, o);
		
	if (this.gotOverlays) {
		this.positionOverlay(overlay);
		if (!overlay.hideOnMouseOut || this.mouseIsOver) 
			diaporama1.animate(overlay, { opacity: overlay.opacity }, overlay.dur);
	}
	diaporama1.push(this.overlays, diaporama1.idCounter - 1);
},
positionOverlay : function(overlay) {
	var p = overlay.position || 'middle center',
		relToVP = (overlay.relativeTo == 'viewport'),
		offX = overlay.offsetX,
		offY = overlay.offsetY;
	if (relToVP) {
		diaporama1.viewport.style.display = 'block';
		overlay.hsKey = this.key;
		if (overlay.offsetWidth > overlay.parentNode.offsetWidth)
			overlay.style.width = '100%';
	} else
	if (overlay.parentNode != this.overlayBox) this.overlayBox.appendChild(overlay);
	if (/left$/.test(p)) overlay.style.left = offX +'px'; 
	
	if (/center$/.test(p))	diaporama1.setStyles (overlay, { 
		left: '50%',
		marginLeft: (offX - Math.round(overlay.offsetWidth / 2)) +'px'
	});	
	
	if (/right$/.test(p)) overlay.style.right = - offX +'px';
		
	if (/^leftpanel$/.test(p)) { 
		diaporama1.setStyles(overlay, {
			right: '100%',
			marginRight: this.x.cb +'px',
			top: - this.y.cb +'px',
			bottom: - this.y.cb +'px',
			overflow: 'auto'
		});		 
		this.x.p1 = overlay.offsetWidth;
	
	} else if (/^rightpanel$/.test(p)) {
		diaporama1.setStyles(overlay, {
			left: '100%',
			marginLeft: this.x.cb +'px',
			top: - this.y.cb +'px',
			bottom: - this.y.cb +'px',
			overflow: 'auto'
		});
		this.x.p2 = overlay.offsetWidth;
	}
	var parOff = overlay.parentNode.offsetHeight;
	overlay.style.height = 'auto';
	if (relToVP && overlay.offsetHeight > parOff)
		overlay.style.height = diaporama1.ieLt7 ? parOff +'px' : '100%';

	if (/^top/.test(p)) overlay.style.top = offY +'px'; 
	if (/^middle/.test(p))	diaporama1.setStyles (overlay, { 
		top: '50%', 
		marginTop: (offY - Math.round(overlay.offsetHeight / 2)) +'px'
	});	
	if (/^bottom/.test(p)) overlay.style.bottom = - offY +'px';
	if (/^above$/.test(p)) {
		diaporama1.setStyles(overlay, {
			left: (- this.x.p1 - this.x.cb) +'px',
			right: (- this.x.p2 - this.x.cb) +'px',
			bottom: '100%',
			marginBottom: this.y.cb +'px',
			width: 'auto'
		});
		this.y.p1 = overlay.offsetHeight;
	
	} else if (/^below$/.test(p)) {
		diaporama1.setStyles(overlay, {
			position: 'relative',
			left: (- this.x.p1 - this.x.cb) +'px',
			right: (- this.x.p2 - this.x.cb) +'px',
			top: '100%',
			marginTop: this.y.cb +'px',
			width: 'auto'
		});
		this.y.p2 = overlay.offsetHeight;
		overlay.style.position = 'absolute';
	}
},

getOverlays : function() {	
	this.getInline(['heading', 'caption'], true);
	this.getNumber();
	if (this.heading && this.dragByHeading) this.heading.className += ' highslide-move';
	if (diaporama1.showCredits) this.writeCredits();
	for (var i = 0; i < diaporama1.overlays.length; i++) {
		var o = diaporama1.overlays[i], tId = o.thumbnailId, sg = o.slideshowGroup;
		if ((!tId && !sg) || (tId && tId == this.thumbsUserSetId)
				|| (sg && sg === this.slideshowGroup)) {
			this.createOverlay(o);
		}
	}
	var os = [];
	for (var i = 0; i < this.overlays.length; i++) {
		var o = diaporama1.$('hsId'+ this.overlays[i]);
		if (/panel$/.test(o.position)) this.positionOverlay(o);
		else diaporama1.push(os, o);
	}
	for (var i = 0; i < os.length; i++) this.positionOverlay(os[i]);
	this.gotOverlays = true;
},
genOverlayBox : function() {
	if (!this.overlayBox) this.overlayBox = diaporama1.createElement (
		'div', {
			className: this.wrapperClassName
		}, {
			position : 'absolute',
			width: (this.x.size || (this.useBox ? this.width : null) 
				|| this.x.full) +'px',
			height: (this.y.size || this.y.full) +'px',
			visibility : 'hidden',
			overflow : 'hidden',
			zIndex : diaporama1.ie ? 4 : 'auto'
		},
		diaporama1.container,
		true
	);
},
sizeOverlayBox : function(doWrapper, doPanels) {
	var overlayBox = this.overlayBox, 
		x = this.x,
		y = this.y;
	diaporama1.setStyles( overlayBox, {
		width: x.size +'px', 
		height: y.size +'px'
	});
	if (doWrapper || doPanels) {
		for (var i = 0; i < this.overlays.length; i++) {
			var o = diaporama1.$('hsId'+ this.overlays[i]);
			var ie6 = (diaporama1.ieLt7 || document.compatMode == 'BackCompat');
			if (o && /^(above|below)$/.test(o.position)) {
				if (ie6) {
					o.style.width = (overlayBox.offsetWidth + 2 * x.cb
						+ x.p1 + x.p2) +'px';
				}
				y[o.position == 'above' ? 'p1' : 'p2'] = o.offsetHeight;
			}
			if (o && ie6 && /^(left|right)panel$/.test(o.position)) {
				o.style.height = (overlayBox.offsetHeight + 2* y.cb) +'px';
			}
		}
	}
	if (doWrapper) {
		diaporama1.setStyles(this.content, {
			top: y.p1 +'px'
		});
		diaporama1.setStyles(overlayBox, {
			top: (y.p1 + y.cb) +'px'
		});
	}
},

showOverlays : function() {
	var b = this.overlayBox;
	b.className = '';
	diaporama1.setStyles(b, {
		top: (this.y.p1 + this.y.cb) +'px',
		left: (this.x.p1 + this.x.cb) +'px',
		overflow : 'visible'
	});
	if (diaporama1.safari) b.style.visibility = 'visible';
	this.wrapper.appendChild (b);
	for (var i = 0; i < this.overlays.length; i++) {
		var o = diaporama1.$('hsId'+ this.overlays[i]);
		o.style.zIndex = o.hsId == 'controls' ? 5 : 4;
		if (!o.hideOnMouseOut || this.mouseIsOver) {
			o.style.visibility = 'visible';
			diaporama1.setStyles(o, { visibility: 'visible', display: '' });
			diaporama1.animate(o, { opacity: o.opacity }, o.dur);
		}
	}
},

destroyOverlays : function() {
	if (!this.overlays.length) return;
	if (this.slideshow) {
		var c = this.slideshow.controls;
		if (c && diaporama1.getExpander(c) == this) c.parentNode.removeChild(c);
	}
	for (var i = 0; i < this.overlays.length; i++) {
		var o = diaporama1.$('hsId'+ this.overlays[i]);
		if (o && o.parentNode == diaporama1.viewport && diaporama1.getExpander(o) == this) diaporama1.discardElement(o);
	}
	diaporama1.discardElement(this.overlayBox);
},



createFullExpand : function () {
	if (this.slideshow && this.slideshow.controls) {
		this.slideshow.enable('full-expand');
		return;
	}
	this.fullExpandLabel = diaporama1.createElement(
		'a', {
			href: 'javascript:diaporama1.expanders['+ this.key +'].doFullExpand();',
			title: diaporama1.lang.fullExpandTitle,
			className: 'highslide-full-expand'
		}
	);
	
	this.createOverlay({ 
		overlayId: this.fullExpandLabel, 
		position: diaporama1.fullExpandPosition, 
		hideOnMouseOut: true, 
		opacity: diaporama1.fullExpandOpacity
	});
},

doFullExpand : function () {
	try {
		if (this.fullExpandLabel) diaporama1.discardElement(this.fullExpandLabel);
		
		this.focus();
		var xSize = this.x.size;
		this.resizeTo(this.x.full, this.y.full);
		
		var xpos = this.x.pos - (this.x.size - xSize) / 2;
		if (xpos < diaporama1.marginLeft) xpos = diaporama1.marginLeft;
		
		this.moveTo(xpos, this.y.pos);
		this.doShowHide('hidden');
	
	} catch (e) {
		this.error(e);
	}
},


afterClose : function () {
	this.a.className = this.a.className.replace('highslide-active-anchor', '');
	
	this.doShowHide('visible');
		if (this.outline && this.outlineWhileAnimating) this.outline.destroy();
	
		diaporama1.discardElement(this.wrapper);
	this.destroyOverlays();
	if (!diaporama1.viewport.childNodes.length) diaporama1.viewport.style.display = 'none';
	
	if (this.dimmingOpacity) diaporama1.undim(this.key);
	diaporama1.expanders[this.key] = null;		
	diaporama1.reOrder();
}

};


diaporama1.Slideshow = function (expKey, options) {
	if (diaporama1.dynamicallyUpdateAnchors !== false) diaporama1.updateAnchors();
	this.expKey = expKey;
	for (var x in options) this[x] = options[x];
	if (this.useControls) this.getControls();
	if (this.thumbstrip) this.thumbstrip = diaporama1.Thumbstrip(this);
};
diaporama1.Slideshow.prototype = {
getControls: function() {
	this.controls = diaporama1.createElement('div', { innerHTML: diaporama1.replaceLang(diaporama1.skin.controls) }, 
		null, diaporama1.container);
	
	var buttons = ['play', 'pause', 'previous', 'next', 'move', 'full-expand', 'close'];
	this.btn = {};
	var pThis = this;
	for (var i = 0; i < buttons.length; i++) {
		this.btn[buttons[i]] = diaporama1.getElementByClass(this.controls, 'li', 'highslide-'+ buttons[i]);
		this.enable(buttons[i]);
	}
	this.btn.pause.style.display = 'none';
	//this.disable('full-expand');
},
checkFirstAndLast: function() {
	if (this.repeat || !this.controls) return;
	var exp = diaporama1.expanders[this.expKey],
		cur = exp.getAnchorIndex(), 
		re = /disabled$/;
	if (cur == 0) 
		this.disable('previous');
	else if (re.test(this.btn.previous.getElementsByTagName('a')[0].className))
		this.enable('previous');
	if (cur + 1 == diaporama1.anchors.groups[exp.slideshowGroup || 'none'].length) {
		this.disable('next');
		this.disable('play');
	} else if (re.test(this.btn.next.getElementsByTagName('a')[0].className)) {
		this.enable('next');
		this.enable('play');
	}
},
enable: function(btn) {
	if (!this.btn) return;
	var sls = this, a = this.btn[btn].getElementsByTagName('a')[0], re = /disabled$/;
	a.onclick = function() {
		sls[btn]();
		return false;
	};
	if (re.test(a.className)) a.className = a.className.replace(re, '');
},
disable: function(btn) {
	if (!this.btn) return;
	var a = this.btn[btn].getElementsByTagName('a')[0];
	a.onclick = function() { return false; };
	if (!/disabled$/.test(a.className)) a.className += ' disabled';
},
hitSpace: function() {
	if (this.autoplay) this.pause();
	else this.play();
},
play: function(wait) {
	if (this.btn) {
		this.btn.play.style.display = 'none';
		this.btn.pause.style.display = '';
	}
	
	this.autoplay = true;	
	if (!wait) diaporama1.next(this.expKey);
},
pause: function() {
	if (this.btn) {
		this.btn.pause.style.display = 'none';
		this.btn.play.style.display = '';
	}
	
	clearTimeout(this.autoplay);
	this.autoplay = null;
},
previous: function() {
	this.pause();
	diaporama1.previous(this.btn.previous);
},
next: function() {
	this.pause();
	diaporama1.next(this.btn.next);
},
move: function() {},
'full-expand': function() {
	diaporama1.getExpander().doFullExpand();
},
close: function() {
	diaporama1.close(this.btn.close);
}
};
diaporama1.Thumbstrip = function(slideshow) {
	function add (exp) {
		diaporama1.extend(options || {}, {
			overlayId: dom,
			hsId: 'thumbstrip',
			className: 'highslide-thumbstrip-'+ mode +'-overlay ' + (options.className || '')
		});
		if (diaporama1.ieLt7) options.fade = 0;
		exp.createOverlay(options);
		diaporama1.setStyles(dom.parentNode, { overflow: 'hidden' });
	};
	
	function scroll (delta) {	
		selectThumb(undefined, Math.round(delta * dom[isX ? 'offsetWidth' : 'offsetHeight'] * 0.7));
	};
	
	function selectThumb (i, scrollBy) {
		if (i === undefined) for (var j = 0; j < group.length; j++) {
			if (group[j] == diaporama1.expanders[slideshow.expKey].a) {
				i = j;
				break;
			}
		}
		if (i === undefined) return;
		var as = dom.getElementsByTagName('a'),
			active = as[i],
			cell = active.parentNode,
			left = isX ? 'Left' : 'Top',
			right = isX ? 'Right' : 'Bottom',
			width = isX ? 'Width' : 'Height',
			offsetLeft = 'offset' + left,
			offsetWidth = 'offset' + width,
			overlayWidth = div.parentNode.parentNode[offsetWidth],
			minTblPos = overlayWidth - table[offsetWidth],
			curTblPos = parseInt(table.style[isX ? 'left' : 'top']) || 0,
			tblPos = curTblPos,
			mgnRight = 20;
		if (scrollBy !== undefined) {
			tblPos = curTblPos - scrollBy;
			if (tblPos > 0) tblPos = 0;
			if (tblPos < minTblPos) tblPos = minTblPos;
	
		} else {
			for (var j = 0; j < as.length; j++) as[j].className = '';
			active.className = 'highslide-active-anchor';
			var activeLeft = i > 0 ? as[i - 1].parentNode[offsetLeft] : cell[offsetLeft],
				activeRight = cell[offsetLeft] + cell[offsetWidth] + 
					(as[i + 1] ? as[i + 1].parentNode[offsetWidth] : 0);
			if (activeRight > overlayWidth - curTblPos) tblPos = overlayWidth - activeRight;
			else if (activeLeft < -curTblPos) tblPos = -activeLeft;
		}
		var markerPos = cell[offsetLeft] + (cell[offsetWidth] - marker[offsetWidth]) / 2 + tblPos;
		diaporama1.animate(table, isX ? { left: tblPos } : { top: tblPos }, null, 'easeOutQuad');
		diaporama1.animate(marker, isX ? { left: markerPos } : { top: markerPos }, null, 'easeOutQuad');
		scrollUp.style.display = tblPos < 0 ? 'block' : 'none';
		scrollDown.style.display = (tblPos > minTblPos)  ? 'block' : 'none';
		
	};
	

	// initialize
	var group = diaporama1.anchors.groups[diaporama1.expanders[slideshow.expKey].slideshowGroup || 'none'],
		options = slideshow.thumbstrip,
		mode = options.mode || 'horizontal',
		floatMode = (mode == 'float'),
		tree = floatMode ? ['div', 'ul', 'li', 'span'] : ['table', 'tbody', 'tr', 'td'],
		isX = (mode == 'horizontal'),
		dom = diaporama1.createElement('div', {
				className: 'highslide-thumbstrip highslide-thumbstrip-'+ mode,
				innerHTML:
					'<div class="highslide-thumbstrip-inner">'+
					'<'+ tree[0] +'><'+ tree[1] +'></'+ tree[1] +'></'+ tree[0] +'></div>'+
					'<div class="highslide-scroll-up"><div></div></div>'+
					'<div class="highslide-scroll-down"><div></div></div>'+
					'<div class="highslide-marker"><div></div></div>'
			}, {
				display: 'none'
			}, diaporama1.container),
		domCh = dom.childNodes,
		div = domCh[0],
		scrollUp = domCh[1],
		scrollDown = domCh[2],
		marker = domCh[3],
		table = div.firstChild,
		tbody = dom.getElementsByTagName(tree[1])[0],
		tr;
	for (var i = 0; i < group.length; i++) {
		if (i == 0 || !isX) tr = diaporama1.createElement(tree[2], null, null, tbody);
		(function(){
			var a = group[i],
				cell = diaporama1.createElement(tree[3], null, null, tr),
				pI = i;
			diaporama1.createElement('a', {
				href: a.href,
				onclick: function() {
					diaporama1.getExpander(this).focus();
					return diaporama1.transit(a);
				},
				innerHTML: diaporama1.stripItemFormatter ? diaporama1.stripItemFormatter(a) : a.innerHTML
			}, null, cell);
		})();
	}
	if (!floatMode) {
		scrollUp.onclick = function () { scroll(-1); };
		scrollDown.onclick = function() { scroll(1); };
		diaporama1.addEventListener(tbody, document.onmousewheel !== undefined ? 
				'mousewheel' : 'DOMMouseScroll', function(e) {        
			var delta = 0;
	        e = e || window.event;
	        if (e.wheelDelta) {
				delta = e.wheelDelta/120;
				if (diaporama1.opera) delta = -delta;
	        } else if (e.detail) {
				delta = -e.detail/3;
	        }
	        if (delta) scroll(-delta * 0.2);
			if (e.preventDefault) e.preventDefault();
			e.returnValue = false;
		});
	}
	
	return {
		add: add,
		selectThumb: selectThumb
	}
};
diaporama1.langDefaults = diaporama1.lang;
// history
var HsExpander = diaporama1.Expander;
if (diaporama1.ie) {
	(function () {
		try {
			document.documentElement.doScroll('left');
		} catch (e) {
			setTimeout(arguments.callee, 50);
			return;
		}
		diaporama1.ready();
	})();
}
diaporama1.addEventListener(document, 'DOMContentLoaded', diaporama1.ready);
diaporama1.addEventListener(window, 'load', diaporama1.ready);

// set handlers
diaporama1.addEventListener(document, 'ready', function() {
	if (diaporama1.expandCursor || diaporama1.dimmingOpacity) {
		var style = diaporama1.createElement('style', { type: 'text/css' }, null, 
			document.getElementsByTagName('HEAD')[0]);
			
		function addRule(sel, dec) {		
			if (!diaporama1.ie) {
				style.appendChild(document.createTextNode(sel + " {" + dec + "}"));
			} else {
				var last = document.styleSheets[document.styleSheets.length - 1];
				if (typeof(last.addRule) == "object") last.addRule(sel, dec);
			}
		}
		function fix(prop) {
			return 'expression( ( ( ignoreMe = document.documentElement.'+ prop +
				' ? document.documentElement.'+ prop +' : document.body.'+ prop +' ) ) + \'px\' );';
		}
		if (diaporama1.expandCursor) addRule ('.highslide img', 
			'cursor: url('+ diaporama1.graphicsDir + diaporama1.expandCursor +'), pointer !important;');
    	addRule ('.highslide-viewport-size',
			(diaporama1.ieLt7 || (diaporama1.ie && document.compatMode == 'BackCompat')) ?
				'position: absolute; '+
				'left:'+ fix('scrollLeft') +
				'top:'+ fix('scrollTop') +
				'width:'+ fix('clientWidth') +
				'height:'+ fix('clientHeight') :
				'position: fixed; width: 100%; height: 100%; left: 0; top: 0');
	}
});
diaporama1.addEventListener(window, 'resize', function() {
	diaporama1.getPageSize();
	if (diaporama1.viewport) for (var i = 0; i < diaporama1.viewport.childNodes.length; i++) {
		var node = diaporama1.viewport.childNodes[i],
			exp = diaporama1.getExpander(node);
		exp.positionOverlay(node);
		if (node.hsId == 'thumbstrip') exp.slideshow.thumbstrip.selectThumb();
	}
});
diaporama1.addEventListener(document, 'mousemove', function(e) {
	diaporama1.mouse = { x: e.clientX, y: e.clientY	};
});
diaporama1.addEventListener(document, 'mousedown', diaporama1.mouseClickHandler);
diaporama1.addEventListener(document, 'mouseup', diaporama1.mouseClickHandler);

diaporama1.addEventListener(document, 'ready', diaporama1.getAnchors);
diaporama1.addEventListener(window, 'load', diaporama1.preloadImages);
}

























/******************************************************************************
Name:    Highslide JS
Version: 4.1.7 (September 28 2009)
Config:  default +slideshow +positioning +transitions +viewport +thumbstrip
Author:  Torstein Hønsi
Support: http://highslide.com/support

Licence:
Highslide JS is licensed under a Creative Commons Attribution-NonCommercial 2.5
License (http://creativecommons.org/licenses/by-nc/2.5/).

You are free:
	* to copy, distribute, display, and perform the work
	* to make derivative works

Under the following conditions:
	* Attribution. You must attribute the work in the manner  specified by  the
	  author or licensor.
	* Noncommercial. You may not use this work for commercial purposes.

* For  any  reuse  or  distribution, you  must make clear to others the license
  terms of this work.
* Any  of  these  conditions  can  be  waived  if  you  get permission from the 
  copyright holder.

Your fair use and other rights are in no way affected by the above.
******************************************************************************/
if (!diaporama2) { var diaporama2 = {
// Language strings
lang : {
	cssDirection: 'ltr',
	loadingText : 'Chargement...',
	loadingTitle : 'Cliquez pour quitter',
	focusTitle : 'Cliquez pour agrandir l image',
	fullExpandTitle : 'Taille reelle (f)',
	creditsText : 'Diaporama <i>R2T</i>',
	creditsTitle : 'R2T',
	previousText : 'Precedant',
	nextText : 'Suivant', 
	moveText : 'Deplacer',
	closeText : 'Fermer', 
	closeTitle : 'Fermer (esc)', 
	resizeTitle : 'Redimensionner',
	playText : 'Lire',
	playTitle : 'Lire (espace)',
	pauseText : 'Pause',
	pauseTitle : 'Pause Diapo (espace)',
	previousTitle : 'Precedant (Fleche gauche)',
	nextTitle : 'Suivant (Fleche droite)',
	moveTitle : 'Deplacer',
	fullExpandText : '1:1',
	number: 'Image %1 of %2',
	restoreTitle : 'Cliquez ici pour terminer le diaporama, bouger le curseur pour deplacer l image .Utilisez les fleches pour parcourir les images du diaporama.'
},
// See http://highslide.com/ref for examples of settings  
graphicsDir : 'highslide/graphics/',
expandCursor : 'zoomin.cur', // null disables
restoreCursor : 'zoomout.cur', // null disables
expandDuration : 250, // milliseconds
restoreDuration : 250,
marginLeft : 15,
marginRight : 15,
marginTop : 15,
marginBottom : 15,
zIndexCounter : 1001, // adjust to other absolutely positioned elements
loadingOpacity : 0.75,
allowMultipleInstances: true,
numberOfImagesToPreload : 5,
outlineWhileAnimating : 2, // 0 = never, 1 = always, 2 = HTML only 
outlineStartOffset : 3, // ends at 10
padToMinWidth : false, // pad the popup width to make room for wide caption
fullExpandPosition : 'bottom right',
fullExpandOpacity : 1,
showCredits : true, // you can set this to false if you want
creditsHref : 'http://highslide.com/',
creditsTarget : '_self',
enableKeyListener : true,
openerTagNames : ['a'], // Add more to allow slideshow indexing
transitions : [],
transitionDuration: 250,
dimmingOpacity: 0, // Lightbox style dimming background
dimmingDuration: 50, // 0 for instant dimming

anchor : 'auto', // where the image expands from
align : 'auto', // position in the client (overrides anchor)
targetX: null, // the id of a target element
targetY: null,
dragByHeading: true,
minWidth: 200,
minHeight: 200,
allowSizeReduction: true, // allow the image to reduce to fit client size. If false, this overrides minWidth and minHeight
outlineType : 'drop-shadow', // set null to disable outlines
skin : {
	controls:
		'<div class="highslide-controls"><ul>'+
			'<li class="highslide-previous">'+
				'<a href="#" title="{diaporama2.lang.previousTitle}">'+
				'<span>{diaporama2.lang.previousText}</span></a>'+
			'</li>'+
			'<li class="highslide-play">'+
				'<a href="#" title="{diaporama2.lang.playTitle}">'+
				'<span>{diaporama2.lang.playText}</span></a>'+
			'</li>'+
			'<li class="highslide-pause">'+
				'<a href="#" title="{diaporama2.lang.pauseTitle}">'+
				'<span>{diaporama2.lang.pauseText}</span></a>'+
			'</li>'+
			'<li class="highslide-next">'+
				'<a href="#" title="{diaporama2.lang.nextTitle}">'+
				'<span>{diaporama2.lang.nextText}</span></a>'+
			'</li>'+
			'<li class="highslide-move">'+
				'<a href="#" title="{diaporama2.lang.moveTitle}">'+
				'<span>{diaporama2.lang.moveText}</span></a>'+
			'</li>'+
			'<li class="highslide-full-expand">'+
				'<a href="#" title="{diaporama2.lang.fullExpandTitle}">'+
				'<span>{diaporama2.lang.fullExpandText}</span></a>'+
			'</li>'+
			'<li class="highslide-close">'+
				'<a href="#" title="{diaporama2.lang.closeTitle}" >'+
				'<span>{diaporama2.lang.closeText}</span></a>'+
			'</li>'+
		'</ul></div>'
},
// END OF YOUR SETTINGS


// declare internal properties
preloadTheseImages : [],
continuePreloading: true,
expanders : [],
overrides : [
	'allowSizeReduction',
	'useBox',
	'anchor',
	'align',
	'targetX',
	'targetY',
	'outlineType',
	'outlineWhileAnimating',
	'captionId',
	'captionText',
	'captionEval',
	'captionOverlay',
	'headingId',
	'headingText',
	'headingEval',
	'headingOverlay',
	'creditsPosition',
	'dragByHeading',
	'autoplay',
	'numberPosition',
	'transitions',
	'dimmingOpacity',
	
	'width',
	'height',
	
	'wrapperClassName',
	'minWidth',
	'minHeight',
	'maxWidth',
	'maxHeight',
	'slideshowGroup',
	'easing',
	'easingClose',
	'fadeInOut',
	'src'
],
overlays : [],
idCounter : 0,
oPos : {
	x: ['leftpanel', 'left', 'center', 'right', 'rightpanel'],
	y: ['above', 'top', 'middle', 'bottom', 'below']
},
mouse: {},
headingOverlay: {},
captionOverlay: {},
timers : [],

slideshows : [],

pendingOutlines : {},
clones : {},
onReady: [],
uaVersion: /Trident\/4\.0/.test(navigator.userAgent) ? 8 :
	parseFloat((navigator.userAgent.toLowerCase().match( /.+(?:rv|it|ra|ie)[\/: ]([\d.]+)/ ) || [0,'0'])[1]),
ie : (document.all && !window.opera),
safari : /Safari/.test(navigator.userAgent),
geckoMac : /Macintosh.+rv:1\.[0-8].+Gecko/.test(navigator.userAgent),

$ : function (id) {
	if (id) return document.getElementById(id);
},

push : function (arr, val) {
	arr[arr.length] = val;
},

createElement : function (tag, attribs, styles, parent, nopad) {
	var el = document.createElement(tag);
	if (attribs) diaporama2.extend(el, attribs);
	if (nopad) diaporama2.setStyles(el, {padding: 0, border: 'none', margin: 0});
	if (styles) diaporama2.setStyles(el, styles);
	if (parent) parent.appendChild(el);	
	return el;
},

extend : function (el, attribs) {
	for (var x in attribs) el[x] = attribs[x];
	return el;
},

setStyles : function (el, styles) {
	for (var x in styles) {
		if (diaporama2.ie && x == 'opacity') {
			if (styles[x] > 0.99) el.style.removeAttribute('filter');
			else el.style.filter = 'alpha(opacity='+ (styles[x] * 100) +')';
		}
		else el.style[x] = styles[x];
	}
},
animate: function(el, prop, opt) {
	var start,
		end,
		unit;
	if (typeof opt != 'object' || opt === null) {
		var args = arguments;
		opt = {
			duration: args[2],
			easing: args[3],
			complete: args[4]
		};
	}
	if (typeof opt.duration != 'number') opt.duration = 250;
	opt.easing = Math[opt.easing] || Math.easeInQuad;
	opt.curAnim = diaporama2.extend({}, prop);
	for (var name in prop) {
		var e = new diaporama2.fx(el, opt , name );
		
		start = parseFloat(diaporama2.css(el, name)) || 0;
		end = parseFloat(prop[name]);
		unit = name != 'opacity' ? 'px' : '';
		
		e.custom( start, end, unit );
	}	
},
css: function(el, prop) {
	if (document.defaultView) {
		return document.defaultView.getComputedStyle(el, null).getPropertyValue(prop);

	} else {
		if (prop == 'opacity') prop = 'filter';
		var val = el.currentStyle[prop.replace(/\-(\w)/g, function (a, b){ return b.toUpperCase(); })];
		if (prop == 'filter') 
			val = val.replace(/alpha\(opacity=([0-9]+)\)/, 
				function (a, b) { return b / 100 });
		return val === '' ? 1 : val;
	} 
},

getPageSize : function () {
	var d = document, w = window, iebody = d.compatMode && d.compatMode != 'BackCompat' 
		? d.documentElement : d.body;
	
	var width = diaporama2.ie ? iebody.clientWidth : 
			(d.documentElement.clientWidth || self.innerWidth),
		height = diaporama2.ie ? iebody.clientHeight : self.innerHeight;
	
	diaporama2.page = {
		width: width,
		height: height,		
		scrollLeft: diaporama2.ie ? iebody.scrollLeft : pageXOffset,
		scrollTop: diaporama2.ie ? iebody.scrollTop : pageYOffset
	}
},

getPosition : function(el)	{
	var p = { x: el.offsetLeft, y: el.offsetTop };
	while (el.offsetParent)	{
		el = el.offsetParent;
		p.x += el.offsetLeft;
		p.y += el.offsetTop;
		if (el != document.body && el != document.documentElement) {
			p.x -= el.scrollLeft;
			p.y -= el.scrollTop;
		}
	}
	return p;
},

expand : function(a, params, custom, type) {
	if (!a) a = diaporama2.createElement('a', null, { display: 'none' }, diaporama2.container);
	if (typeof a.getParams == 'function') return params;	
	try {	
		new diaporama2.Expander(a, params, custom);
		return false;
	} catch (e) { return true; }
},
getElementByClass : function (el, tagName, className) {
	var els = el.getElementsByTagName(tagName);
	for (var i = 0; i < els.length; i++) {
    	if ((new RegExp(className)).test(els[i].className)) {
			return els[i];
		}
	}
	return null;
},
replaceLang : function(s) {
	s = s.replace(/\s/g, ' ');
	var re = /{diaporama2\.lang\.([^}]+)\}/g,
		matches = s.match(re),
		lang;
	if (matches) for (var i = 0; i < matches.length; i++) {
		lang = matches[i].replace(re, "$1");
		if (typeof diaporama2.lang[lang] != 'undefined') s = s.replace(matches[i], diaporama2.lang[lang]);
	}
	return s;
},


focusTopmost : function() {
	var topZ = 0, 
		topmostKey = -1,
		expanders = diaporama2.expanders,
		exp,
		zIndex;
	for (var i = 0; i < expanders.length; i++) {
		exp = expanders[i];
		if (exp) {
			zIndex = exp.wrapper.style.zIndex;
			if (zIndex && zIndex > topZ) {
				topZ = zIndex;				
				topmostKey = i;
			}
		}
	}
	if (topmostKey == -1) diaporama2.focusKey = -1;
	else expanders[topmostKey].focus();
},

getParam : function (a, param) {
	a.getParams = a.onclick;
	var p = a.getParams ? a.getParams() : null;
	a.getParams = null;
	
	return (p && typeof p[param] != 'undefined') ? p[param] : 
		(typeof diaporama2[param] != 'undefined' ? diaporama2[param] : null);
},

getSrc : function (a) {
	var src = diaporama2.getParam(a, 'src');
	if (src) return src;
	return a.href;
},

getNode : function (id) {
	var node = diaporama2.$(id), clone = diaporama2.clones[id], a = {};
	if (!node && !clone) return null;
	if (!clone) {
		clone = node.cloneNode(true);
		clone.id = '';
		diaporama2.clones[id] = clone;
		return node;
	} else {
		return clone.cloneNode(true);
	}
},

discardElement : function(d) {
	if (d) diaporama2.garbageBin.appendChild(d);
	diaporama2.garbageBin.innerHTML = '';
},
dim : function(exp) {
	function fix(prop) {
		return 'expression( ( ( ignoreMe = document.documentElement.'+ prop +
			' ? document.documentElement.'+ prop +' : document.body.'+ prop +' ) ) + \'px\' );';
	}

	if (!diaporama2.dimmer) {
		diaporama2.dimmer = diaporama2.createElement ('div', {
				className: 'highslide-dimming highslide-viewport-size',
				owner: '',
				onclick: function() {
					
						diaporama2.close();
				}
			}, {
                visibility: 'visible',
				opacity: 0
			}, diaporama2.container, true);
	}

	diaporama2.dimmer.style.display = '';

	diaporama2.dimmer.owner += '|'+ exp.key;
	if (diaporama2.geckoMac && diaporama2.dimmingGeckoFix)
		diaporama2.setStyles(diaporama2.dimmer, {
			background: 'url('+ diaporama2.graphicsDir + 'geckodimmer.png)',
			opacity: 1
		});
	else
		diaporama2.animate(diaporama2.dimmer, { opacity: exp.dimmingOpacity }, diaporama2.dimmingDuration);
},
undim : function(key) {
	if (!diaporama2.dimmer) return;
	if (typeof key != 'undefined') diaporama2.dimmer.owner = diaporama2.dimmer.owner.replace('|'+ key, '');

	if (
		(typeof key != 'undefined' && diaporama2.dimmer.owner != '')
		|| (diaporama2.upcoming && diaporama2.getParam(diaporama2.upcoming, 'dimmingOpacity'))
	) return;

	if (diaporama2.geckoMac && diaporama2.dimmingGeckoFix) diaporama2.dimmer.style.display = 'none';
	else diaporama2.animate(diaporama2.dimmer, { opacity: 0 }, diaporama2.dimmingDuration, null, function() {
		diaporama2.dimmer.style.display = 'none';
	});
},
transit : function (adj, exp) {
	var last = exp = exp || diaporama2.getExpander();
	if (diaporama2.upcoming) return false;
	else diaporama2.last = last;
	try {
		diaporama2.upcoming = adj;
		adj.onclick(); 		
	} catch (e){
		diaporama2.last = diaporama2.upcoming = null;
	}
	try {
		if (!adj || exp.transitions[1] != 'crossfade')
		exp.close();
	} catch (e) {}
	return false;
},

previousOrNext : function (el, op) {
	var exp = diaporama2.getExpander(el);
	if (exp) return diaporama2.transit(exp.getAdjacentAnchor(op), exp);
	else return false;
},

previous : function (el) {
	return diaporama2.previousOrNext(el, -1);
},

next : function (el) {
	return diaporama2.previousOrNext(el, 1);	
},

keyHandler : function(e) {
	if (!e) e = window.event;
	if (!e.target) e.target = e.srcElement; // ie
	if (typeof e.target.form != 'undefined') return true; // form element has focus
	var exp = diaporama2.getExpander();
	
	var op = null;
	switch (e.keyCode) {
		case 70: // f
			if (exp) exp.doFullExpand();
			return true;
		case 32: // Space
			op = 2;
			break;
		case 34: // Page Down
		case 39: // Arrow right
		case 40: // Arrow down
			op = 1;
			break;
		case 8:  // Backspace
		case 33: // Page Up
		case 37: // Arrow left
		case 38: // Arrow up
			op = -1;
			break;
		case 27: // Escape
		case 13: // Enter
			op = 0;
	}
	if (op !== null) {if (op != 2)diaporama2.removeEventListener(document, window.opera ? 'keypress' : 'keydown', diaporama2.keyHandler);
		if (!diaporama2.enableKeyListener) return true;
		
		if (e.preventDefault) e.preventDefault();
    	else e.returnValue = false;
    	if (exp) {
			if (op == 0) {
				exp.close();
			} else if (op == 2) {
				if (exp.slideshow) exp.slideshow.hitSpace();
			} else {
				if (exp.slideshow) exp.slideshow.pause();
				diaporama2.previousOrNext(exp.key, op);
			}
			return false;
		}
	}
	return true;
},


registerOverlay : function (overlay) {
	diaporama2.push(diaporama2.overlays, diaporama2.extend(overlay, { hsId: 'hsId'+ diaporama2.idCounter++ } ));
},


addSlideshow : function (options) {
	var sg = options.slideshowGroup;
	if (typeof sg == 'object') {
		for (var i = 0; i < sg.length; i++) {
			var o = {};
			for (var x in options) o[x] = options[x];
			o.slideshowGroup = sg[i];
			diaporama2.push(diaporama2.slideshows, o);
		}
	} else {
		diaporama2.push(diaporama2.slideshows, options);
	}
},

getWrapperKey : function (element, expOnly) {
	var el, re = /^highslide-wrapper-([0-9]+)$/;
	// 1. look in open expanders
	el = element;
	while (el.parentNode)	{
		if (el.hsKey !== undefined) return el.hsKey;
		if (el.id && re.test(el.id)) return el.id.replace(re, "$1");
		el = el.parentNode;
	}
	// 2. look in thumbnail
	if (!expOnly) {
		el = element;
		while (el.parentNode)	{
			if (el.tagName && diaporama2.isHsAnchor(el)) {
				for (var key = 0; key < diaporama2.expanders.length; key++) {
					var exp = diaporama2.expanders[key];
					if (exp && exp.a == el) return key;
				}
			}
			el = el.parentNode;
		}
	}
	return null; 
},

getExpander : function (el, expOnly) {
	if (typeof el == 'undefined') return diaporama2.expanders[diaporama2.focusKey] || null;
	if (typeof el == 'number') return diaporama2.expanders[el] || null;
	if (typeof el == 'string') el = diaporama2.$(el);
	return diaporama2.expanders[diaporama2.getWrapperKey(el, expOnly)] || null;
},

isHsAnchor : function (a) {
	return (a.onclick && a.onclick.toString().replace(/\s/g, ' ').match(/diaporama2.(htmlE|e)xpand/));
},

reOrder : function () {
	for (var i = 0; i < diaporama2.expanders.length; i++)
		if (diaporama2.expanders[i] && diaporama2.expanders[i].isExpanded) diaporama2.focusTopmost();
},

mouseClickHandler : function(e) 
{	
	if (!e) e = window.event;
	if (e.button > 1) return true;
	if (!e.target) e.target = e.srcElement;
	
	var el = e.target;
	while (el.parentNode
		&& !(/highslide-(image|move|html|resize)/.test(el.className)))
	{
		el = el.parentNode;
	}
	var exp = diaporama2.getExpander(el);
	if (exp && (exp.isClosing || !exp.isExpanded)) return true;
		
	if (exp && e.type == 'mousedown') {
		if (e.target.form) return true;
		var match = el.className.match(/highslide-(image|move|resize)/);
		if (match) {
			diaporama2.dragArgs = { exp: exp , type: match[1], left: exp.x.pos, width: exp.x.size, top: exp.y.pos, 
				height: exp.y.size, clickX: e.clientX, clickY: e.clientY };
			
			
			diaporama2.addEventListener(document, 'mousemove', diaporama2.dragHandler);
			if (e.preventDefault) e.preventDefault(); // FF
			
			if (/highslide-(image|html)-blur/.test(exp.content.className)) {
				exp.focus();
				diaporama2.hasFocused = true;
			}
			return false;
		}
	} else if (e.type == 'mouseup') {
		
		diaporama2.removeEventListener(document, 'mousemove', diaporama2.dragHandler);
		
		if (diaporama2.dragArgs) {
			if (diaporama2.styleRestoreCursor && diaporama2.dragArgs.type == 'image') 
				diaporama2.dragArgs.exp.content.style.cursor = diaporama2.styleRestoreCursor;
			var hasDragged = diaporama2.dragArgs.hasDragged;
			
			if (!hasDragged &&!diaporama2.hasFocused && !/(move|resize)/.test(diaporama2.dragArgs.type)) {
				exp.close();
			} 
			else if (hasDragged || (!hasDragged && diaporama2.hasHtmlExpanders)) {
				diaporama2.dragArgs.exp.doShowHide('hidden');
			}
			diaporama2.hasFocused = false;
			diaporama2.dragArgs = null;
		
		} else if (/highslide-image-blur/.test(el.className)) {
			el.style.cursor = diaporama2.styleRestoreCursor;		
		}
	}
	return false;
},

dragHandler : function(e)
{
	if (!diaporama2.dragArgs) return true;
	if (!e) e = window.event;
	var a = diaporama2.dragArgs, exp = a.exp;
	
	a.dX = e.clientX - a.clickX;
	a.dY = e.clientY - a.clickY;	
	
	var distance = Math.sqrt(Math.pow(a.dX, 2) + Math.pow(a.dY, 2));
	if (!a.hasDragged) a.hasDragged = (a.type != 'image' && distance > 0)
		|| (distance > (diaporama2.dragSensitivity || 5));
	
	if (a.hasDragged && e.clientX > 5 && e.clientY > 5) {
		
		if (a.type == 'resize') exp.resize(a);
		else {
			exp.moveTo(a.left + a.dX, a.top + a.dY);
			if (a.type == 'image') exp.content.style.cursor = 'move';
		}
	}
	return false;
},

wrapperMouseHandler : function (e) {
	try {
		if (!e) e = window.event;
		var over = /mouseover/i.test(e.type); 
		if (!e.target) e.target = e.srcElement; // ie
		if (diaporama2.ie) e.relatedTarget = 
			over ? e.fromElement : e.toElement; // ie
		var exp = diaporama2.getExpander(e.target);
		if (!exp.isExpanded) return;
		if (!exp || !e.relatedTarget || diaporama2.getExpander(e.relatedTarget, true) == exp 
			|| diaporama2.dragArgs) return;
		for (var i = 0; i < exp.overlays.length; i++) (function() {
			var o = diaporama2.$('hsId'+ exp.overlays[i]);
			if (o && o.hideOnMouseOut) {
				if (over) diaporama2.setStyles(o, { visibility: 'visible', display: '' });
				diaporama2.animate(o, { opacity: over ? o.opacity : 0 }, o.dur);
			}
		})();	
	} catch (e) {}
},
addEventListener : function (el, event, func) {
	if (el == document && event == 'ready') diaporama2.push(diaporama2.onReady, func);
	try {
		el.addEventListener(event, func, false);
	} catch (e) {
		try {
			el.detachEvent('on'+ event, func);
			el.attachEvent('on'+ event, func);
		} catch (e) {
			el['on'+ event] = func;
		}
	} 
},

removeEventListener : function (el, event, func) {
	try {
		el.removeEventListener(event, func, false);
	} catch (e) {
		try {
			el.detachEvent('on'+ event, func);
		} catch (e) {
			el['on'+ event] = null;
		}
	}
},

preloadFullImage : function (i) {
	if (diaporama2.continuePreloading && diaporama2.preloadTheseImages[i] && diaporama2.preloadTheseImages[i] != 'undefined') {
		var img = document.createElement('img');
		img.onload = function() { 
			img = null;
			diaporama2.preloadFullImage(i + 1);
		};
		img.src = diaporama2.preloadTheseImages[i];
	}
},
preloadImages : function (number) {
	if (number && typeof number != 'object') diaporama2.numberOfImagesToPreload = number;
	
	var arr = diaporama2.getAnchors();
	for (var i = 0; i < arr.images.length && i < diaporama2.numberOfImagesToPreload; i++) {
		diaporama2.push(diaporama2.preloadTheseImages, diaporama2.getSrc(arr.images[i]));
	}
	
	// preload outlines
	if (diaporama2.outlineType)	new diaporama2.Outline(diaporama2.outlineType, function () { diaporama2.preloadFullImage(0)} );
	else
	
	diaporama2.preloadFullImage(0);
	
	// preload cursor
	if (diaporama2.restoreCursor) var cur = diaporama2.createElement('img', { src: diaporama2.graphicsDir + diaporama2.restoreCursor });
},


init : function () {
	if (!diaporama2.container) {
	
		diaporama2.getPageSize();
		diaporama2.ieLt7 = diaporama2.ie && diaporama2.uaVersion < 7;
		for (var x in diaporama2.langDefaults) {
			if (typeof diaporama2[x] != 'undefined') diaporama2.lang[x] = diaporama2[x];
			else if (typeof diaporama2.lang[x] == 'undefined' && typeof diaporama2.langDefaults[x] != 'undefined') 
				diaporama2.lang[x] = diaporama2.langDefaults[x];
		}
		
		diaporama2.container = diaporama2.createElement('div', {
				className: 'highslide-container'
			}, {
				position: 'absolute', 
				left: 0, 
				top: 0, 
				width: '100%', 
				zIndex: diaporama2.zIndexCounter,
				direction: 'ltr'
			}, 
			document.body,
			true
		);
		diaporama2.loading = diaporama2.createElement('a', {
				className: 'highslide-loading',
				title: diaporama2.lang.loadingTitle,
				innerHTML: diaporama2.lang.loadingText,
				href: 'javascript:;'
			}, {
				position: 'absolute',
				top: '-9999px',
				opacity: diaporama2.loadingOpacity,
				zIndex: 1
			}, diaporama2.container
		);
		diaporama2.garbageBin = diaporama2.createElement('div', null, { display: 'none' }, diaporama2.container);
		diaporama2.viewport = diaporama2.createElement('div', {
				className: 'highslide-viewport highslide-viewport-size'
			}, {
				visibility: (diaporama2.safari && diaporama2.uaVersion < 525) ? 'visible' : 'hidden'
			}, diaporama2.container, 1
		);
		
		// http://www.robertpenner.com/easing/ 
		Math.linearTween = function (t, b, c, d) {
			return c*t/d + b;
		};
		Math.easeInQuad = function (t, b, c, d) {
			return c*(t/=d)*t + b;
		};
		Math.easeOutQuad = function (t, b, c, d) {
			return -c *(t/=d)*(t-2) + b;
		};
		
		diaporama2.hideSelects = diaporama2.ieLt7;
		diaporama2.hideIframes = ((window.opera && diaporama2.uaVersion < 9) || navigator.vendor == 'KDE' 
			|| (diaporama2.ie && diaporama2.uaVersion < 5.5));
	}
},
ready : function() {
	if (diaporama2.isReady) return;
	diaporama2.isReady = true;
	
	for (var i = 0; i < diaporama2.onReady.length; i++) diaporama2.onReady[i]();
},

updateAnchors : function() {
	var el, els, all = [], images = [],groups = {}, re;
		
	for (var i = 0; i < diaporama2.openerTagNames.length; i++) {
		els = document.getElementsByTagName(diaporama2.openerTagNames[i]);
		for (var j = 0; j < els.length; j++) {
			el = els[j];
			re = diaporama2.isHsAnchor(el);
			if (re) {
				diaporama2.push(all, el);
				if (re[0] == 'diaporama2.expand') diaporama2.push(images, el);
				var g = diaporama2.getParam(el, 'slideshowGroup') || 'none';
				if (!groups[g]) groups[g] = [];
				diaporama2.push(groups[g], el);
			}
		}
	}
	diaporama2.anchors = { all: all, groups: groups, images: images };
	return diaporama2.anchors;
	
},

getAnchors : function() {
	return diaporama2.anchors || diaporama2.updateAnchors();
},


close : function(el) {
	var exp = diaporama2.getExpander(el);
	if (exp) exp.close();
	return false;
}
}; // end diaporama2 object
diaporama2.fx = function( elem, options, prop ){
	this.options = options;
	this.elem = elem;
	this.prop = prop;

	if (!options.orig) options.orig = {};
};
diaporama2.fx.prototype = {
	update: function(){
		(diaporama2.fx.step[this.prop] || diaporama2.fx.step._default)(this);
		
		if (this.options.step)
			this.options.step.call(this.elem, this.now, this);

	},
	custom: function(from, to, unit){
		this.startTime = (new Date()).getTime();
		this.start = from;
		this.end = to;
		this.unit = unit;// || this.unit || "px";
		this.now = this.start;
		this.pos = this.state = 0;

		var self = this;
		function t(gotoEnd){
			return self.step(gotoEnd);
		}

		t.elem = this.elem;

		if ( t() && diaporama2.timers.push(t) == 1 ) {
			diaporama2.timerId = setInterval(function(){
				var timers = diaporama2.timers;

				for ( var i = 0; i < timers.length; i++ )
					if ( !timers[i]() )
						timers.splice(i--, 1);

				if ( !timers.length ) {
					clearInterval(diaporama2.timerId);
				}
			}, 13);
		}
	},
	step: function(gotoEnd){
		var t = (new Date()).getTime();
		if ( gotoEnd || t >= this.options.duration + this.startTime ) {
			this.now = this.end;
			this.pos = this.state = 1;
			this.update();

			this.options.curAnim[ this.prop ] = true;

			var done = true;
			for ( var i in this.options.curAnim )
				if ( this.options.curAnim[i] !== true )
					done = false;

			if ( done ) {
				if (this.options.complete) this.options.complete.call(this.elem);
			}
			return false;
		} else {
			var n = t - this.startTime;
			this.state = n / this.options.duration;
			this.pos = this.options.easing(n, 0, 1, this.options.duration);
			this.now = this.start + ((this.end - this.start) * this.pos);
			this.update();
		}
		return true;
	}

};

diaporama2.extend( diaporama2.fx, {
	step: {

		opacity: function(fx){
			diaporama2.setStyles(fx.elem, { opacity: fx.now });
		},

		_default: function(fx){
			try {
				if ( fx.elem.style && fx.elem.style[ fx.prop ] != null )
					fx.elem.style[ fx.prop ] = fx.now + fx.unit;
				else
					fx.elem[ fx.prop ] = fx.now;
			} catch (e) {}
		}
	}
});

diaporama2.Outline =  function (outlineType, onLoad) {
	this.onLoad = onLoad;
	this.outlineType = outlineType;
	var v = diaporama2.uaVersion, tr;
	
	this.hasAlphaImageLoader = diaporama2.ie && v >= 5.5 && v < 7;
	if (!outlineType) {
		if (onLoad) onLoad();
		return;
	}
	
	diaporama2.init();
	this.table = diaporama2.createElement(
		'table', { 
			cellSpacing: 0 
		}, {
			visibility: 'hidden',
			position: 'absolute',
			borderCollapse: 'collapse',
			width: 0
		},
		diaporama2.container,
		true
	);
	var tbody = diaporama2.createElement('tbody', null, null, this.table, 1);
	
	this.td = [];
	for (var i = 0; i <= 8; i++) {
		if (i % 3 == 0) tr = diaporama2.createElement('tr', null, { height: 'auto' }, tbody, true);
		this.td[i] = diaporama2.createElement('td', null, null, tr, true);
		var style = i != 4 ? { lineHeight: 0, fontSize: 0} : { position : 'relative' };
		diaporama2.setStyles(this.td[i], style);
	}
	this.td[4].className = outlineType +' highslide-outline';
	
	this.preloadGraphic(); 
};

diaporama2.Outline.prototype = {
preloadGraphic : function () {
	var src = diaporama2.graphicsDir + (diaporama2.outlinesDir || "outlines/")+ this.outlineType +".png";
				
	var appendTo = diaporama2.safari ? diaporama2.container : null;
	this.graphic = diaporama2.createElement('img', null, { position: 'absolute', 
		top: '-9999px' }, appendTo, true); // for onload trigger
	
	var pThis = this;
	this.graphic.onload = function() { pThis.onGraphicLoad(); };
	
	this.graphic.src = src;
},

onGraphicLoad : function () {
	var o = this.offset = this.graphic.width / 4,
		pos = [[0,0],[0,-4],[-2,0],[0,-8],0,[-2,-8],[0,-2],[0,-6],[-2,-2]],
		dim = { height: (2*o) +'px', width: (2*o) +'px' };
	for (var i = 0; i <= 8; i++) {
		if (pos[i]) {
			if (this.hasAlphaImageLoader) {
				var w = (i == 1 || i == 7) ? '100%' : this.graphic.width +'px';
				var div = diaporama2.createElement('div', null, { width: '100%', height: '100%', position: 'relative', overflow: 'hidden'}, this.td[i], true);
				diaporama2.createElement ('div', null, { 
						filter: "progid:DXImageTransform.Microsoft.AlphaImageLoader(sizingMethod=scale, src='"+ this.graphic.src + "')", 
						position: 'absolute',
						width: w, 
						height: this.graphic.height +'px',
						left: (pos[i][0]*o)+'px',
						top: (pos[i][1]*o)+'px'
					}, 
				div,
				true);
			} else {
				diaporama2.setStyles(this.td[i], { background: 'url('+ this.graphic.src +') '+ (pos[i][0]*o)+'px '+(pos[i][1]*o)+'px'});
			}
			
			if (window.opera && (i == 3 || i ==5)) 
				diaporama2.createElement('div', null, dim, this.td[i], true);
			
			diaporama2.setStyles (this.td[i], dim);
		}
	}
	this.graphic = null;
	if (diaporama2.pendingOutlines[this.outlineType]) diaporama2.pendingOutlines[this.outlineType].destroy();
	diaporama2.pendingOutlines[this.outlineType] = this;
	if (this.onLoad) this.onLoad();
},
	
setPosition : function (pos, offset, vis, dur, easing) {
	var exp = this.exp,
		stl = exp.wrapper.style,
		offset = offset || 0,
		pos = pos || {
			x: exp.x.pos + offset,
			y: exp.y.pos + offset,
			w: exp.x.get('wsize') - 2 * offset,
			h: exp.y.get('wsize') - 2 * offset
		};
	if (vis) this.table.style.visibility = (pos.h >= 4 * this.offset) 
		? 'visible' : 'hidden';
	diaporama2.setStyles(this.table, {
		left: (pos.x - this.offset) +'px',
		top: (pos.y - this.offset) +'px',
		width: (pos.w + 2 * this.offset) +'px'
	});
	
	pos.w -= 2 * this.offset;
	pos.h -= 2 * this.offset;
	diaporama2.setStyles (this.td[4], {
		width: pos.w >= 0 ? pos.w +'px' : 0,
		height: pos.h >= 0 ? pos.h +'px' : 0
	});
	if (this.hasAlphaImageLoader) this.td[3].style.height 
		= this.td[5].style.height = this.td[4].style.height;	
	
},
	
destroy : function(hide) {
	if (hide) this.table.style.visibility = 'hidden';
	else diaporama2.discardElement(this.table);
}
};

diaporama2.Dimension = function(exp, dim) {
	this.exp = exp;
	this.dim = dim;
	this.ucwh = dim == 'x' ? 'Width' : 'Height';
	this.wh = this.ucwh.toLowerCase();
	this.uclt = dim == 'x' ? 'Left' : 'Top';
	this.lt = this.uclt.toLowerCase();
	this.ucrb = dim == 'x' ? 'Right' : 'Bottom';
	this.rb = this.ucrb.toLowerCase();
	this.p1 = this.p2 = 0;
};
diaporama2.Dimension.prototype = {
get : function(key) {
	switch (key) {
		case 'loadingPos':
			return this.tpos + this.tb + (this.t - diaporama2.loading['offset'+ this.ucwh]) / 2;
		case 'loadingPosXfade':
			return this.pos + this.cb+ this.p1 + (this.size - diaporama2.loading['offset'+ this.ucwh]) / 2;
		case 'wsize':
			return this.size + 2 * this.cb + this.p1 + this.p2;
		case 'fitsize':
			return this.clientSize - this.marginMin - this.marginMax;
		case 'maxsize':
			return this.get('fitsize') - 2 * this.cb - this.p1 - this.p2 ;
		case 'opos':
			return this.pos - (this.exp.outline ? this.exp.outline.offset : 0);
		case 'osize':
			return this.get('wsize') + (this.exp.outline ? 2*this.exp.outline.offset : 0);
		case 'imgPad':
			return this.imgSize ? Math.round((this.size - this.imgSize) / 2) : 0;
		
	}
},
calcBorders: function() {
	// correct for borders
	this.cb = (this.exp.content['offset'+ this.ucwh] - this.t) / 2;
	
	this.marginMax = diaporama2['margin'+ this.ucrb];
},
calcThumb: function() {
	this.t = this.exp.el[this.wh] ? parseInt(this.exp.el[this.wh]) : 
		this.exp.el['offset'+ this.ucwh];
	this.tpos = this.exp.tpos[this.dim];
	this.tb = (this.exp.el['offset'+ this.ucwh] - this.t) / 2;
	if (this.tpos == 0 || this.tpos == -1) {
		this.tpos = (diaporama2.page[this.wh] / 2) + diaporama2.page['scroll'+ this.uclt];		
	};
},
calcExpanded: function() {
	var exp = this.exp;
	this.justify = 'auto';
	
	// get alignment
	if (exp.align == 'center') this.justify = 'center';
	else if (new RegExp(this.lt).test(exp.anchor)) this.justify = null;
	else if (new RegExp(this.rb).test(exp.anchor)) this.justify = 'max';
	
	
	// size and position
	this.pos = this.tpos - this.cb + this.tb;
	
	if (this.dim == 'x')
		exp.maxWidth = Math.min(exp.maxWidth || this.full, exp.maxHeight * this.full / exp.y.full); 
		
	this.size = Math.min(this.full, exp['max'+ this.ucwh] || this.full);
	this.minSize = exp.allowSizeReduction ? 
		Math.min(exp['min'+ this.ucwh], this.full) :this.full;
	if (exp.isImage && exp.useBox)	{
		this.size = exp[this.wh];
		this.imgSize = this.full;
	}
	if (this.dim == 'x' && diaporama2.padToMinWidth) this.minSize = exp.minWidth;
	this.target = exp['target'+ this.dim.toUpperCase()];
	this.marginMin = diaporama2['margin'+ this.uclt];
	this.scroll = diaporama2.page['scroll'+ this.uclt];
	this.clientSize = diaporama2.page[this.wh];
},
setSize: function(i) {
	var exp = this.exp;
	if (exp.isImage && (exp.useBox || diaporama2.padToMinWidth)) {
		this.imgSize = i;
		this.size = Math.max(this.size, this.imgSize);
		exp.content.style[this.lt] = this.get('imgPad')+'px';
	} else
	this.size = i;

	exp.content.style[this.wh] = i +'px';
	exp.wrapper.style[this.wh] = this.get('wsize') +'px';
	if (exp.outline) exp.outline.setPosition();
	if (this.dim == 'x' && exp.overlayBox) exp.sizeOverlayBox(true);
	if (this.dim == 'x' && exp.slideshow && exp.isImage) {
		if (i == this.full) exp.slideshow.disable('full-expand');
		else exp.slideshow.enable('full-expand');
	}
},
setPos: function(i) {
	this.pos = i;
	this.exp.wrapper.style[this.lt] = i +'px';	
	
	if (this.exp.outline) this.exp.outline.setPosition();
	
}
};

diaporama2.Expander = function(a, params, custom, contentType) {
	if (document.readyState && diaporama2.ie && !diaporama2.isReady) {
		diaporama2.addEventListener(document, 'ready', function() {
			new diaporama2.Expander(a, params, custom, contentType);
		});
		return;
	} 
	this.a = a;
	this.custom = custom;
	this.contentType = contentType || 'image';
	this.isImage = !this.isHtml;
	
	diaporama2.continuePreloading = false;
	this.overlays = [];
	this.last = diaporama2.last;
	diaporama2.last = null;
	diaporama2.init();
	var key = this.key = diaporama2.expanders.length;
	// override inline parameters
	for (var i = 0; i < diaporama2.overrides.length; i++) {
		var name = diaporama2.overrides[i];
		this[name] = params && typeof params[name] != 'undefined' ?
			params[name] : diaporama2[name];
	}
	if (!this.src) this.src = a.href;
	
	// get thumb
	var el = (params && params.thumbnailId) ? diaporama2.$(params.thumbnailId) : a;
	el = this.thumb = el.getElementsByTagName('img')[0] || el;
	this.thumbsUserSetId = el.id || a.id;
	
	// check if already open
	for (var i = 0; i < diaporama2.expanders.length; i++) {
		if (diaporama2.expanders[i] && diaporama2.expanders[i].a == a 
			&& !(this.last && this.transitions[1] == 'crossfade')) {
			diaporama2.expanders[i].focus();
			return false;
		}
	}	

	// cancel other
	if (!diaporama2.allowSimultaneousLoading) for (var i = 0; i < diaporama2.expanders.length; i++) {
		if (diaporama2.expanders[i] && diaporama2.expanders[i].thumb != el && !diaporama2.expanders[i].onLoadStarted) {
			diaporama2.expanders[i].cancelLoading();
		}
	}
	diaporama2.expanders[key] = this;
	if (!diaporama2.allowMultipleInstances && !diaporama2.upcoming) {
		if (diaporama2.expanders[key-1]) diaporama2.expanders[key-1].close();
		if (typeof diaporama2.focusKey != 'undefined' && diaporama2.expanders[diaporama2.focusKey])
			diaporama2.expanders[diaporama2.focusKey].close();
	}
	
	// initiate metrics
	this.el = el;
	this.tpos = diaporama2.getPosition(el);
	diaporama2.getPageSize();
	var x = this.x = new diaporama2.Dimension(this, 'x');
	x.calcThumb();
	var y = this.y = new diaporama2.Dimension(this, 'y');
	y.calcThumb();
	this.wrapper = diaporama2.createElement(
		'div', {
			id: 'highslide-wrapper-'+ this.key,
			className: 'highslide-wrapper '+ this.wrapperClassName
		}, {
			visibility: 'hidden',
			position: 'absolute',
			zIndex: diaporama2.zIndexCounter += 2
		}, null, true );
	
	this.wrapper.onmouseover = this.wrapper.onmouseout = diaporama2.wrapperMouseHandler;
	if (this.contentType == 'image' && this.outlineWhileAnimating == 2)
		this.outlineWhileAnimating = 0;
	
	// get the outline
	if (!this.outlineType 
		|| (this.last && this.isImage && this.transitions[1] == 'crossfade')) {
		this[this.contentType +'Create']();
	
	} else if (diaporama2.pendingOutlines[this.outlineType]) {
		this.connectOutline();
		this[this.contentType +'Create']();
	
	} else {
		this.showLoading();
		var exp = this;
		new diaporama2.Outline(this.outlineType, 
			function () {
				exp.connectOutline();
				exp[exp.contentType +'Create']();
			} 
		);
	}
	return true;
};

diaporama2.Expander.prototype = {
error : function(e) {
	// alert ('Line '+ e.lineNumber +': '+ e.message);
	window.location.href = this.src;
},

connectOutline : function() {
	var outline = this.outline = diaporama2.pendingOutlines[this.outlineType];
	outline.exp = this;
	outline.table.style.zIndex = this.wrapper.style.zIndex - 1;
	diaporama2.pendingOutlines[this.outlineType] = null;
},

showLoading : function() {
	if (this.onLoadStarted || this.loading) return;
	
	this.loading = diaporama2.loading;
	var exp = this;
	this.loading.onclick = function() {
		exp.cancelLoading();
	};
	var exp = this, 
		l = this.x.get('loadingPos') +'px',
		t = this.y.get('loadingPos') +'px';
	if (!tgt && this.last && this.transitions[1] == 'crossfade') 
		var tgt = this.last; 
	if (tgt) {
		l = tgt.x.get('loadingPosXfade') +'px';
		t = tgt.y.get('loadingPosXfade') +'px';
		this.loading.style.zIndex = diaporama2.zIndexCounter++;
	}
	setTimeout(function () { 
		if (exp.loading) diaporama2.setStyles(exp.loading, { left: l, top: t, zIndex: diaporama2.zIndexCounter++ })}
	, 100);
},

imageCreate : function() {
	var exp = this;
	
	var img = document.createElement('img');
    this.content = img;
    img.onload = function () {
    	if (diaporama2.expanders[exp.key]) exp.contentLoaded(); 
	};
    if (diaporama2.blockRightClick) img.oncontextmenu = function() { return false; };
    img.className = 'highslide-image';
    diaporama2.setStyles(img, {
    	visibility: 'hidden',
    	display: 'block',
    	position: 'absolute',
		maxWidth: '9999px',
		zIndex: 3
	});
    img.title = diaporama2.lang.restoreTitle;
    if (diaporama2.safari) diaporama2.container.appendChild(img);
    if (diaporama2.ie && diaporama2.flushImgSize) img.src = null;
	img.src = this.src;
	
	this.showLoading();
},

contentLoaded : function() {
	try {	
		if (!this.content) return;
		this.content.onload = null;
		if (this.onLoadStarted) return;
		else this.onLoadStarted = true;
		
		var x = this.x, y = this.y;
		
		if (this.loading) {
			diaporama2.setStyles(this.loading, { top: '-9999px' });
			this.loading = null;
		}	
			x.full = this.content.width;
			y.full = this.content.height;
			
			diaporama2.setStyles(this.content, {
				width: x.t +'px',
				height: y.t +'px'
			});
			this.wrapper.appendChild(this.content);
			diaporama2.container.appendChild(this.wrapper);
		
		x.calcBorders();
		y.calcBorders();
		
		diaporama2.setStyles (this.wrapper, {
			left: (x.tpos + x.tb - x.cb) +'px',
			top: (y.tpos + x.tb - y.cb) +'px'
		});
		
		
		this.initSlideshow();
		this.getOverlays();
		
		var ratio = x.full / y.full;
		x.calcExpanded();
		this.justify(x);
		
		y.calcExpanded();
		this.justify(y);
		if (this.overlayBox) this.sizeOverlayBox(0, 1);
		
		if (this.allowSizeReduction) {
				this.correctRatio(ratio);
			var ss = this.slideshow;			
			if (ss && this.last && ss.controls && ss.fixedControls) {
				var pos = ss.overlayOptions.position || '', p;
				for (var dim in diaporama2.oPos) for (var i = 0; i < 5; i++) {
					p = this[dim];
					if (pos.match(diaporama2.oPos[dim][i])) {
						p.pos = this.last[dim].pos 
							+ (this.last[dim].p1 - p.p1)
							+ (this.last[dim].size - p.size) * [0, 0, .5, 1, 1][i];
						if (ss.fixedControls == 'fit') {
							if (p.pos + p.size + p.p1 + p.p2 > p.scroll + p.clientSize - p.marginMax)
								p.pos = p.scroll + p.clientSize - p.size - p.marginMin - p.marginMax - p.p1 - p.p2;
							if (p.pos < p.scroll + p.marginMin) p.pos = p.scroll + p.marginMin; 
						} 
					}
				}
			}
			if (this.isImage && this.x.full > (this.x.imgSize || this.x.size)) {
				this.createFullExpand();
				if (this.overlays.length == 1) this.sizeOverlayBox();
			}
		}
		this.show();
		
	} catch (e) {
		this.error(e);
	}
},

justify : function (p, moveOnly) {
	var tgtArr, tgt = p.target, dim = p == this.x ? 'x' : 'y';
	
	if (tgt && tgt.match(/ /)) {
		tgtArr = tgt.split(' ');
		tgt = tgtArr[0];
	}
	if (tgt && diaporama2.$(tgt)) {
		p.pos = diaporama2.getPosition(diaporama2.$(tgt))[dim];
		if (tgtArr && tgtArr[1] && tgtArr[1].match(/^[-]?[0-9]+px$/)) 
			p.pos += parseInt(tgtArr[1]);
		if (p.size < p.minSize) p.size = p.minSize;
		
	} else if (p.justify == 'auto' || p.justify == 'center') {
	
		var hasMovedMin = false;
		
		var allowReduce = p.exp.allowSizeReduction;
		if (p.justify == 'center')
			p.pos = Math.round(p.scroll + (p.clientSize + p.marginMin - p.marginMax - p.get('wsize')) / 2);
		else
			p.pos = Math.round(p.pos - ((p.get('wsize') - p.t) / 2));
		if (p.pos < p.scroll + p.marginMin) {
			p.pos = p.scroll + p.marginMin;
			hasMovedMin = true;		
		}
		if (!moveOnly && p.size < p.minSize) {
			p.size = p.minSize;
			allowReduce = false;
		}
		if (p.pos + p.get('wsize') > p.scroll + p.clientSize - p.marginMax) {
			if (!moveOnly && hasMovedMin && allowReduce) {
				p.size = Math.min(p.size, p.get(dim == 'y' ? 'fitsize' : 'maxsize'));
			} else if (p.get('wsize') < p.get('fitsize')) {
				p.pos = p.scroll + p.clientSize - p.marginMax - p.get('wsize');
			} else { // image larger than viewport
				p.pos = p.scroll + p.marginMin;
				if (!moveOnly && allowReduce) p.size = p.get(dim == 'y' ? 'fitsize' : 'maxsize');
			}			
		}
		
		if (!moveOnly && p.size < p.minSize) {
			p.size = p.minSize;
			allowReduce = false;
		}
		
	
	} else if (p.justify == 'max') {
		p.pos = Math.floor(p.pos - p.size + p.t);
	}
	
		
	if (p.pos < p.marginMin) {
		var tmpMin = p.pos;
		p.pos = p.marginMin; 
		
		if (allowReduce && !moveOnly) p.size = p.size - (p.pos - tmpMin);
		
	}
},

correctRatio : function(ratio) {
	var x = this.x, 
		y = this.y,
		changed = false,
		xSize = Math.min(x.full, x.size),
		ySize = Math.min(y.full, y.size),
		useBox = (this.useBox || diaporama2.padToMinWidth);
	
	if (xSize / ySize > ratio) { // width greater
		xSize = ySize * ratio;
		if (xSize < x.minSize) { // below minWidth
			xSize = x.minSize;
			ySize = xSize / ratio;
		}
		changed = true;
	
	} else if (xSize / ySize < ratio) { // height greater
		ySize = xSize / ratio;
		changed = true;
	}
	
	if (diaporama2.padToMinWidth && x.full < x.minSize) {
		x.imgSize = x.full;
		y.size = y.imgSize = y.full;
	} else if (this.useBox) {
		x.imgSize = xSize;
		y.imgSize = ySize;
	} else {
		x.size = xSize;
		y.size = ySize;
	}
	changed = this.fitOverlayBox(useBox ? null : ratio, changed);
	if (useBox && y.size < y.imgSize) {
		y.imgSize = y.size;
		x.imgSize = y.size * ratio;
	}
	if (changed || useBox) {
		x.pos = x.tpos - x.cb + x.tb;
		x.minSize = x.size;
		this.justify(x, true);
	
		y.pos = y.tpos - y.cb + y.tb;
		y.minSize = y.size;
		this.justify(y, true);
		if (this.overlayBox) this.sizeOverlayBox();
	}
},
fitOverlayBox : function(ratio, changed) {
	var x = this.x, y = this.y;
	if (this.overlayBox) {
		while (y.size > this.minHeight && x.size > this.minWidth 
				&&  y.get('wsize') > y.get('fitsize')) {
			y.size -= 10;
			if (ratio) x.size = y.size * ratio;
			this.sizeOverlayBox(0, 1);
			changed = true;
		}
	}
	return changed;
},

show : function () {
	var x = this.x, y = this.y;
	this.doShowHide('hidden');
	if (this.slideshow && this.slideshow.thumbstrip) this.slideshow.thumbstrip.selectThumb();
	
	// Apply size change
	this.changeSize(
		1, {
			wrapper: {
				width : x.get('wsize'),
				height : y.get('wsize'),
				left: x.pos,
				top: y.pos
			},
			content: {
				left: x.p1 + x.get('imgPad'),
				top: y.p1 + y.get('imgPad'),
				width:x.imgSize ||x.size,
				height:y.imgSize ||y.size
			}
		},
		diaporama2.expandDuration
	);
},

changeSize : function(up, to, dur) {
	// transition
	var trans = this.transitions,
	other = up ? (this.last ? this.last.a : null) : diaporama2.upcoming,
	t = (trans[1] && other 
			&& diaporama2.getParam(other, 'transitions')[1] == trans[1]) ?
		trans[1] : trans[0];
		
	if (this[t] && t != 'expand') {
		this[t](up, to);
		return;
	}
	
	if (this.outline && !this.outlineWhileAnimating) {
		if (up) this.outline.setPosition();
		else this.outline.destroy();
	}
	
	
	if (!up) this.destroyOverlays();
	
	var exp = this,
		x = exp.x,
		y = exp.y,
		easing = this.easing;
	if (!up) easing = this.easingClose || easing;
	var after = up ?
		function() {
				
			if (exp.outline) exp.outline.table.style.visibility = "visible";
			setTimeout(function() {
				exp.afterExpand();
			}, 50);
		} :
		function() {
			exp.afterClose();
		};
	if (up) diaporama2.setStyles( this.wrapper, {
		width: x.t +'px',
		height: y.t +'px'
	});
	if (this.fadeInOut) {
		diaporama2.setStyles(this.wrapper, { opacity: up ? 0 : 1 });
		diaporama2.extend(to.wrapper, { opacity: up });
	}
	diaporama2.animate( this.wrapper, to.wrapper, {
		duration: dur,
		easing: easing,
		step: function(val, args) {
			if (exp.outline && exp.outlineWhileAnimating && args.prop == 'top') {
				var fac = up ? args.pos : 1 - args.pos;
				var pos = {
					w: x.t + (x.get('wsize') - x.t) * fac,
					h: y.t + (y.get('wsize') - y.t) * fac,
					x: x.tpos + (x.pos - x.tpos) * fac,
					y: y.tpos + (y.pos - y.tpos) * fac
				};
				exp.outline.setPosition(pos, 0, 1);				
			}
		}
	});
	diaporama2.animate( this.content, to.content, dur, easing, after);
	if (up) {
		this.wrapper.style.visibility = 'visible';
		this.content.style.visibility = 'visible';
		this.a.className += ' highslide-active-anchor';
	}
},



fade : function(up, to) {
	this.outlineWhileAnimating = false;
	var exp = this,	t = up ? diaporama2.expandDuration : 0;
	
	if (up) {
		diaporama2.animate(this.wrapper, to.wrapper, 0);
		diaporama2.setStyles(this.wrapper, { opacity: 0, visibility: 'visible' });
		diaporama2.animate(this.content, to.content, 0);
		this.content.style.visibility = 'visible';

		diaporama2.animate(this.wrapper, { opacity: 1 }, t, null, 
			function() { exp.afterExpand(); });
	}
	
	if (this.outline) {
		this.outline.table.style.zIndex = this.wrapper.style.zIndex;
		var dir = up || -1, 
			offset = this.outline.offset,
			startOff = up ? 3 : offset,
			endOff = up? offset : 3;
		for (var i = startOff; dir * i <= dir * endOff; i += dir, t += 25) {
			(function() {
				var o = up ? endOff - i : startOff - i;
				setTimeout(function() {
					exp.outline.setPosition(0, o, 1);
				}, t);
			})();
		}
	}
	
	
	if (up) {}//setTimeout(function() { exp.afterExpand(); }, t+50);
	else {
		setTimeout( function() {
			if (exp.outline) exp.outline.destroy(exp.preserveContent);
			
			exp.destroyOverlays();
	
			diaporama2.animate( exp.wrapper, { opacity: 0 }, diaporama2.restoreDuration, null, function(){
				exp.afterClose();
			});
		}, t);		
	}
},
crossfade : function (up, to, from) {
	if (!up) return;
	var exp = this, 
		last = this.last,
		x = this.x,
		y = this.y,
		lastX = last.x,
		lastY = last.y,
		wrapper = this.wrapper,
		content = this.content,
		overlayBox = this.overlayBox;
	diaporama2.removeEventListener(document, 'mousemove', diaporama2.dragHandler);
	
	diaporama2.setStyles(content, { 
		width: (x.imgSize || x.size) +'px', 
		height: (y.imgSize || y.size) +'px'		
	});
	if (overlayBox) overlayBox.style.overflow = 'visible';
	this.outline = last.outline;
	if (this.outline) this.outline.exp = exp;
	last.outline = null;
	var fadeBox = diaporama2.createElement('div', {
			className: 'highslide-image'
		}, { 
			position: 'absolute', 
			zIndex: 4,
			overflow: 'hidden',
			display: 'none'
		}
	);
	var names = { oldImg: last, newImg: this };
	for (var n in names) { 	
		this[n] = names[n].content.cloneNode(1);
		diaporama2.setStyles(this[n], {
			position: 'absolute',
			border: 0,
			visibility: 'visible'
		});
		fadeBox.appendChild(this[n]);
	}
	wrapper.appendChild(fadeBox);
	if (overlayBox) {
		overlayBox.className = '';
		wrapper.appendChild(overlayBox);
	}
	fadeBox.style.display = '';
	last.content.style.display = 'none';
	
	
	if (diaporama2.safari) {
		var match = navigator.userAgent.match(/Safari\/([0-9]{3})/);
		if (match && parseInt(match[1]) < 525) this.wrapper.style.visibility = 'visible';
	}
	diaporama2.animate(wrapper, {
		width: x.size
	}, {
		duration: diaporama2.transitionDuration, 
		step: function(val, args) {
			var pos = args.pos,
				invPos = 1 - pos;
			var prop,
				size = {}, 
				props = ['pos', 'size', 'p1', 'p2'];
			for (var n in props) {
				prop = props[n];
				size['x'+ prop] = invPos * lastX[prop] + pos * x[prop];
				size['y'+ prop] = invPos * lastY[prop] + pos * y[prop];
				size.ximgSize = invPos * (lastX.imgSize || lastX.size) + pos * (x.imgSize || x.size);
				size.ximgPad = invPos * lastX.get('imgPad') + pos * x.get('imgPad');
				size.yimgSize = invPos * (lastY.imgSize || lastY.size) + pos * (y.imgSize || y.size);
				size.yimgPad = invPos * lastY.get('imgPad') + pos * y.get('imgPad');
			}
			if (exp.outline) exp.outline.setPosition({ 
				x: size.xpos, 
				y: size.ypos, 
				w: size.xsize + size.xp1 + size.xp2 + 2 * x.cb, 
				h: size.ysize + size.yp1 + size.yp2 + 2 * y.cb
			});
			last.wrapper.style.clip = 'rect('
				+ (size.ypos - lastY.pos)+'px, '
				+ (size.xsize + size.xp1 + size.xp2 + size.xpos + 2 * lastX.cb - lastX.pos) +'px, '
				+ (size.ysize + size.yp1 + size.yp2 + size.ypos + 2 * lastY.cb - lastY.pos) +'px, '
				+ (size.xpos - lastX.pos)+'px)';
				
			diaporama2.setStyles(content, {
				top: (size.yp1 + y.get('imgPad')) +'px',
				left: (size.xp1 + x.get('imgPad')) +'px',
				marginTop: (y.pos - size.ypos) +'px',
				marginLeft: (x.pos - size.xpos) +'px'
			});
			
			diaporama2.setStyles(wrapper, {
				top: size.ypos +'px',
				left: size.xpos +'px',
				width: (size.xp1 + size.xp2 + size.xsize + 2 * x.cb)+ 'px',
				height: (size.yp1 + size.yp2 + size.ysize + 2 * y.cb) + 'px'
			});
			diaporama2.setStyles(fadeBox, {
				width: (size.ximgSize || size.xsize) + 'px',
				height: (size.yimgSize || size.ysize) +'px',
				left: (size.xp1 + size.ximgPad)  +'px',
				top: (size.yp1 + size.yimgPad) +'px',
				visibility: 'visible'
			});
			
			diaporama2.setStyles(exp.oldImg, {
				top: (lastY.pos - size.ypos + lastY.p1 - size.yp1 + lastY.get('imgPad') - size.yimgPad)+'px',
				left: (lastX.pos - size.xpos + lastX.p1 - size.xp1 + lastX.get('imgPad') - size.ximgPad)+'px'
			});		
			
			diaporama2.setStyles(exp.newImg, {
				opacity: pos,
				top: (y.pos - size.ypos + y.p1 - size.yp1 + y.get('imgPad') - size.yimgPad) +'px',
				left: (x.pos - size.xpos + x.p1 - size.xp1 + x.get('imgPad') - size.ximgPad) +'px'
			});
			if (overlayBox) diaporama2.setStyles(overlayBox, {
				width: size.xsize + 'px',
				height: size.ysize +'px',
				left: (size.xp1 + x.cb)  +'px',
				top: (size.yp1 + y.cb) +'px'
			});
		},
		complete: function () {
			wrapper.style.visibility = content.style.visibility = 'visible';
			content.style.display = 'block';
			fadeBox.style.display = 'none';
			exp.a.className += ' highslide-active-anchor';
			exp.afterExpand();
			last.afterClose();
			exp.last = null;
		}
		
	});
},
reuseOverlay : function(o, el) {
	if (!this.last) return false;
	for (var i = 0; i < this.last.overlays.length; i++) {
		var oDiv = diaporama2.$('hsId'+ this.last.overlays[i]);
		if (oDiv && oDiv.hsId == o.hsId) {
			this.genOverlayBox();
			oDiv.reuse = this.key;
			diaporama2.push(this.overlays, this.last.overlays[i]);
			return true;
		}
	}
	return false;
},


afterExpand : function() {
	this.isExpanded = true;	
	this.focus();
	if (this.dimmingOpacity) diaporama2.dim(this);
	if (diaporama2.upcoming && diaporama2.upcoming == this.a) diaporama2.upcoming = null;
	this.prepareNextOutline();
	var p = diaporama2.page, mX = diaporama2.mouse.x + p.scrollLeft, mY = diaporama2.mouse.y + p.scrollTop;
	this.mouseIsOver = this.x.pos < mX && mX < this.x.pos + this.x.get('wsize')
		&& this.y.pos < mY && mY < this.y.pos + this.y.get('wsize');	
	if (this.overlayBox) this.showOverlays();
	
},


prepareNextOutline : function() {
	var key = this.key;
	var outlineType = this.outlineType;
	new diaporama2.Outline(outlineType, 
		function () { try { diaporama2.expanders[key].preloadNext(); } catch (e) {} });
},


preloadNext : function() {
	var next = this.getAdjacentAnchor(1);
	if (next && next.onclick.toString().match(/diaporama2\.expand/)) 
		var img = diaporama2.createElement('img', { src: diaporama2.getSrc(next) });
},


getAdjacentAnchor : function(op) {
	var current = this.getAnchorIndex(), as = diaporama2.anchors.groups[this.slideshowGroup || 'none'];
	
	/*< ? if ($cfg->slideshow) : ?>s*/
	if (!as[current + op] && this.slideshow && this.slideshow.repeat) {
		if (op == 1) return as[0];
		else if (op == -1) return as[as.length-1];
	}
	/*< ? endif ?>s*/
	return as[current + op] || null;
},

getAnchorIndex : function() {
	var arr = diaporama2.getAnchors().groups[this.slideshowGroup || 'none'];
	if (arr) for (var i = 0; i < arr.length; i++) {
		if (arr[i] == this.a) return i; 
	}
	return null;
},


getNumber : function() {
	if (this[this.numberPosition]) {
		var arr = diaporama2.anchors.groups[this.slideshowGroup || 'none'];
		if (arr) {
			var s = diaporama2.lang.number.replace('%1', this.getAnchorIndex() + 1).replace('%2', arr.length);
			this[this.numberPosition].innerHTML = 
				'<div class="highslide-number">'+ s +'</div>'+ this[this.numberPosition].innerHTML;
		}
	}
},
initSlideshow : function() {
	if (!this.last) {
		for (var i = 0; i < diaporama2.slideshows.length; i++) {
			var ss = diaporama2.slideshows[i], sg = ss.slideshowGroup;
			if (typeof sg == 'undefined' || sg === null || sg === this.slideshowGroup) 
				this.slideshow = new diaporama2.Slideshow(this.key, ss);
		} 
	} else {
		this.slideshow = this.last.slideshow;
	}
	var ss = this.slideshow;
	if (!ss) return;
	var key = ss.expKey = this.key;
	
	ss.checkFirstAndLast();
	ss.disable('full-expand');
	if (ss.controls) {
		var o = ss.overlayOptions || {};
		o.overlayId = ss.controls;
		o.hsId = 'controls';		
		this.createOverlay(o);
	}
	if (ss.thumbstrip) ss.thumbstrip.add(this);
	if (!this.last && this.autoplay) ss.play(true);
	if (ss.autoplay) {
		ss.autoplay = setTimeout(function() {
			diaporama2.next(key);
		}, (ss.interval || 500));
	}
},

cancelLoading : function() {
	diaporama2.discardElement (this.wrapper);
	diaporama2.expanders[this.key] = null;
	if (diaporama2.upcoming == this.a) diaporama2.upcoming = null;
	diaporama2.undim(this.key);
	if (this.loading) diaporama2.loading.style.left = '-9999px';
},

writeCredits : function () {
	if (this.credits) return;
	this.credits = diaporama2.createElement('a', {
		href: diaporama2.creditsHref,
		target: diaporama2.creditsTarget,
		className: 'highslide-credits',
		innerHTML: diaporama2.lang.creditsText,
		title: diaporama2.lang.creditsTitle
	});
	this.createOverlay({ 
		overlayId: this.credits, 
		position: this.creditsPosition || 'top left', 
		hsId: 'credits' 
	});
},

getInline : function(types, addOverlay) {
	for (var i = 0; i < types.length; i++) {
		var type = types[i], s = null;
		if (!this[type +'Id'] && this.thumbsUserSetId)  
			this[type +'Id'] = type +'-for-'+ this.thumbsUserSetId;
		if (this[type +'Id']) this[type] = diaporama2.getNode(this[type +'Id']);
		if (!this[type] && !this[type +'Text'] && this[type +'Eval']) try {
			s = eval(this[type +'Eval']);
		} catch (e) {}
		if (!this[type] && this[type +'Text']) {
			s = this[type +'Text'];
		}
		if (!this[type] && !s) {
			this[type] = diaporama2.getNode(this.a['_'+ type + 'Id']);
			if (!this[type]) {
				var next = this.a.nextSibling;
				while (next && !diaporama2.isHsAnchor(next)) {
					if ((new RegExp('highslide-'+ type)).test(next.className || null)) {
						if (!next.id) this.a['_'+ type + 'Id'] = next.id = 'hsId'+ diaporama2.idCounter++;
						this[type] = diaporama2.getNode(next.id);
						break;
					}
					next = next.nextSibling;
				}
			}
		}
		if (!this[type] && !s && this.numberPosition == type) s = '\n';
		
		if (!this[type] && s) this[type] = diaporama2.createElement('div', 
				{ className: 'highslide-'+ type, innerHTML: s } );
		
		if (addOverlay && this[type]) {
			var o = { position: (type == 'heading') ? 'above' : 'below' };
			for (var x in this[type+'Overlay']) o[x] = this[type+'Overlay'][x];
			o.overlayId = this[type];
			this.createOverlay(o);
		}
	}
},


// on end move and resize
doShowHide : function(visibility) {
	if (diaporama2.hideSelects) this.showHideElements('SELECT', visibility);
	if (diaporama2.hideIframes) this.showHideElements('IFRAME', visibility);
	if (diaporama2.geckoMac) this.showHideElements('*', visibility);
},
showHideElements : function (tagName, visibility) {
	var els = document.getElementsByTagName(tagName);
	var prop = tagName == '*' ? 'overflow' : 'visibility';
	for (var i = 0; i < els.length; i++) {
		if (prop == 'visibility' || (document.defaultView.getComputedStyle(
				els[i], "").getPropertyValue('overflow') == 'auto'
				|| els[i].getAttribute('hidden-by') != null)) {
			var hiddenBy = els[i].getAttribute('hidden-by');
			if (visibility == 'visible' && hiddenBy) {
				hiddenBy = hiddenBy.replace('['+ this.key +']', '');
				els[i].setAttribute('hidden-by', hiddenBy);
				if (!hiddenBy) els[i].style[prop] = els[i].origProp;
			} else if (visibility == 'hidden') { // hide if behind
				var elPos = diaporama2.getPosition(els[i]);
				elPos.w = els[i].offsetWidth;
				elPos.h = els[i].offsetHeight;
				if (!this.dimmingOpacity) { // hide all if dimming
				
					var clearsX = (elPos.x + elPos.w < this.x.get('opos') 
						|| elPos.x > this.x.get('opos') + this.x.get('osize'));
					var clearsY = (elPos.y + elPos.h < this.y.get('opos') 
						|| elPos.y > this.y.get('opos') + this.y.get('osize'));
				}
				var wrapperKey = diaporama2.getWrapperKey(els[i]);
				if (!clearsX && !clearsY && wrapperKey != this.key) { // element falls behind image
					if (!hiddenBy) {
						els[i].setAttribute('hidden-by', '['+ this.key +']');
						els[i].origProp = els[i].style[prop];
						els[i].style[prop] = 'hidden';
						
					} else if (hiddenBy.indexOf('['+ this.key +']') == -1) {
						els[i].setAttribute('hidden-by', hiddenBy + '['+ this.key +']');
					}
				} else if ((hiddenBy == '['+ this.key +']' || diaporama2.focusKey == wrapperKey)
						&& wrapperKey != this.key) { // on move
					els[i].setAttribute('hidden-by', '');
					els[i].style[prop] = els[i].origProp || '';
				} else if (hiddenBy && hiddenBy.indexOf('['+ this.key +']') > -1) {
					els[i].setAttribute('hidden-by', hiddenBy.replace('['+ this.key +']', ''));
				}
						
			}
		}
	}
},

focus : function() {
	this.wrapper.style.zIndex = diaporama2.zIndexCounter += 2;
	// blur others
	for (var i = 0; i < diaporama2.expanders.length; i++) {
		if (diaporama2.expanders[i] && i == diaporama2.focusKey) {
			var blurExp = diaporama2.expanders[i];
			blurExp.content.className += ' highslide-'+ blurExp.contentType +'-blur';
				blurExp.content.style.cursor = diaporama2.ie ? 'hand' : 'pointer';
				blurExp.content.title = diaporama2.lang.focusTitle;
		}
	}
	
	// focus this
	if (this.outline) this.outline.table.style.zIndex 
		= this.wrapper.style.zIndex - 1;
	this.content.className = 'highslide-'+ this.contentType;
		this.content.title = diaporama2.lang.restoreTitle;
		
		if (diaporama2.restoreCursor) {
			diaporama2.styleRestoreCursor = window.opera ? 'pointer' : 'url('+ diaporama2.graphicsDir + diaporama2.restoreCursor +'), pointer';
			if (diaporama2.ie && diaporama2.uaVersion < 6) diaporama2.styleRestoreCursor = 'hand';
			this.content.style.cursor = diaporama2.styleRestoreCursor;
		}
		
	diaporama2.focusKey = this.key;	
	diaporama2.addEventListener(document, window.opera ? 'keypress' : 'keydown', diaporama2.keyHandler);	
},
moveTo: function(x, y) {
	this.x.setPos(x);
	this.y.setPos(y);
},
resize : function (e) {
	var w, h, r = e.width / e.height;
	w = Math.max(e.width + e.dX, Math.min(this.minWidth, this.x.full));
	if (this.isImage && Math.abs(w - this.x.full) < 12) w = this.x.full;
	h = w / r;
	if (h < Math.min(this.minHeight, this.y.full)) {
		h = Math.min(this.minHeight, this.y.full);
		if (this.isImage) w = h * r;
	}
	this.resizeTo(w, h);
},
resizeTo: function(w, h) {
	this.y.setSize(h);
	this.x.setSize(w);
	this.wrapper.style.height = this.y.get('wsize') +'px';
},

close : function() {
	if (this.isClosing || !this.isExpanded) return;
	if (this.transitions[1] == 'crossfade' && diaporama2.upcoming) {
		diaporama2.getExpander(diaporama2.upcoming).cancelLoading();
		diaporama2.upcoming = null;
	}
	this.isClosing = true;
	if (this.slideshow && !diaporama2.upcoming) this.slideshow.pause();
	
	diaporama2.removeEventListener(document, window.opera ? 'keypress' : 'keydown', diaporama2.keyHandler);
	
	try {
		this.content.style.cursor = 'default';
		this.changeSize(
			0, {
				wrapper: {
					width : this.x.t,
					height : this.y.t,
					left: this.x.tpos - this.x.cb + this.x.tb,
					top: this.y.tpos - this.y.cb + this.y.tb
				},
				content: {
					left: 0,
					top: 0,
					width: this.x.t,
					height: this.y.t
				}
			}, diaporama2.restoreDuration
		);
	} catch (e) { this.afterClose(); }
},

createOverlay : function (o) {
	var el = o.overlayId, 
		relToVP = (o.relativeTo == 'viewport' && !/panel$/.test(o.position));
	if (typeof el == 'string') el = diaporama2.getNode(el);
	if (o.html) el = diaporama2.createElement('div', { innerHTML: o.html });
	if (!el || typeof el == 'string') return;
	el.style.display = 'block';
	o.hsId = o.hsId || o.overlayId; 
	if (this.transitions[1] == 'crossfade' && this.reuseOverlay(o, el)) return;
	this.genOverlayBox();
	var width = o.width && /^[0-9]+(px|%)$/.test(o.width) ? o.width : 'auto';
	if (/^(left|right)panel$/.test(o.position) && !/^[0-9]+px$/.test(o.width)) width = '200px';
	var overlay = diaporama2.createElement(
		'div', {
			id: 'hsId'+ diaporama2.idCounter++,
			hsId: o.hsId
		}, {
			position: 'absolute',
			visibility: 'hidden',
			width: width,
			direction: diaporama2.lang.cssDirection || '',
			opacity: 0
		},
		relToVP ? diaporama2.viewport :this.overlayBox,
		true
	);
	if (relToVP) overlay.hsKey = this.key;
	
	overlay.appendChild(el);
	diaporama2.extend(overlay, {
		opacity: 1,
		offsetX: 0,
		offsetY: 0,
		dur: (o.fade === 0 || o.fade === false || (o.fade == 2 && diaporama2.ie)) ? 0 : 250
	});
	diaporama2.extend(overlay, o);
		
	if (this.gotOverlays) {
		this.positionOverlay(overlay);
		if (!overlay.hideOnMouseOut || this.mouseIsOver) 
			diaporama2.animate(overlay, { opacity: overlay.opacity }, overlay.dur);
	}
	diaporama2.push(this.overlays, diaporama2.idCounter - 1);
},
positionOverlay : function(overlay) {
	var p = overlay.position || 'middle center',
		relToVP = (overlay.relativeTo == 'viewport'),
		offX = overlay.offsetX,
		offY = overlay.offsetY;
	if (relToVP) {
		diaporama2.viewport.style.display = 'block';
		overlay.hsKey = this.key;
		if (overlay.offsetWidth > overlay.parentNode.offsetWidth)
			overlay.style.width = '100%';
	} else
	if (overlay.parentNode != this.overlayBox) this.overlayBox.appendChild(overlay);
	if (/left$/.test(p)) overlay.style.left = offX +'px'; 
	
	if (/center$/.test(p))	diaporama2.setStyles (overlay, { 
		left: '50%',
		marginLeft: (offX - Math.round(overlay.offsetWidth / 2)) +'px'
	});	
	
	if (/right$/.test(p)) overlay.style.right = - offX +'px';
		
	if (/^leftpanel$/.test(p)) { 
		diaporama2.setStyles(overlay, {
			right: '100%',
			marginRight: this.x.cb +'px',
			top: - this.y.cb +'px',
			bottom: - this.y.cb +'px',
			overflow: 'auto'
		});		 
		this.x.p1 = overlay.offsetWidth;
	
	} else if (/^rightpanel$/.test(p)) {
		diaporama2.setStyles(overlay, {
			left: '100%',
			marginLeft: this.x.cb +'px',
			top: - this.y.cb +'px',
			bottom: - this.y.cb +'px',
			overflow: 'auto'
		});
		this.x.p2 = overlay.offsetWidth;
	}
	var parOff = overlay.parentNode.offsetHeight;
	overlay.style.height = 'auto';
	if (relToVP && overlay.offsetHeight > parOff)
		overlay.style.height = diaporama2.ieLt7 ? parOff +'px' : '100%';

	if (/^top/.test(p)) overlay.style.top = offY +'px'; 
	if (/^middle/.test(p))	diaporama2.setStyles (overlay, { 
		top: '50%', 
		marginTop: (offY - Math.round(overlay.offsetHeight / 2)) +'px'
	});	
	if (/^bottom/.test(p)) overlay.style.bottom = - offY +'px';
	if (/^above$/.test(p)) {
		diaporama2.setStyles(overlay, {
			left: (- this.x.p1 - this.x.cb) +'px',
			right: (- this.x.p2 - this.x.cb) +'px',
			bottom: '100%',
			marginBottom: this.y.cb +'px',
			width: 'auto'
		});
		this.y.p1 = overlay.offsetHeight;
	
	} else if (/^below$/.test(p)) {
		diaporama2.setStyles(overlay, {
			position: 'relative',
			left: (- this.x.p1 - this.x.cb) +'px',
			right: (- this.x.p2 - this.x.cb) +'px',
			top: '100%',
			marginTop: this.y.cb +'px',
			width: 'auto'
		});
		this.y.p2 = overlay.offsetHeight;
		overlay.style.position = 'absolute';
	}
},

getOverlays : function() {	
	this.getInline(['heading', 'caption'], true);
	this.getNumber();
	if (this.heading && this.dragByHeading) this.heading.className += ' highslide-move';
	if (diaporama2.showCredits) this.writeCredits();
	for (var i = 0; i < diaporama2.overlays.length; i++) {
		var o = diaporama2.overlays[i], tId = o.thumbnailId, sg = o.slideshowGroup;
		if ((!tId && !sg) || (tId && tId == this.thumbsUserSetId)
				|| (sg && sg === this.slideshowGroup)) {
			this.createOverlay(o);
		}
	}
	var os = [];
	for (var i = 0; i < this.overlays.length; i++) {
		var o = diaporama2.$('hsId'+ this.overlays[i]);
		if (/panel$/.test(o.position)) this.positionOverlay(o);
		else diaporama2.push(os, o);
	}
	for (var i = 0; i < os.length; i++) this.positionOverlay(os[i]);
	this.gotOverlays = true;
},
genOverlayBox : function() {
	if (!this.overlayBox) this.overlayBox = diaporama2.createElement (
		'div', {
			className: this.wrapperClassName
		}, {
			position : 'absolute',
			width: (this.x.size || (this.useBox ? this.width : null) 
				|| this.x.full) +'px',
			height: (this.y.size || this.y.full) +'px',
			visibility : 'hidden',
			overflow : 'hidden',
			zIndex : diaporama2.ie ? 4 : 'auto'
		},
		diaporama2.container,
		true
	);
},
sizeOverlayBox : function(doWrapper, doPanels) {
	var overlayBox = this.overlayBox, 
		x = this.x,
		y = this.y;
	diaporama2.setStyles( overlayBox, {
		width: x.size +'px', 
		height: y.size +'px'
	});
	if (doWrapper || doPanels) {
		for (var i = 0; i < this.overlays.length; i++) {
			var o = diaporama2.$('hsId'+ this.overlays[i]);
			var ie6 = (diaporama2.ieLt7 || document.compatMode == 'BackCompat');
			if (o && /^(above|below)$/.test(o.position)) {
				if (ie6) {
					o.style.width = (overlayBox.offsetWidth + 2 * x.cb
						+ x.p1 + x.p2) +'px';
				}
				y[o.position == 'above' ? 'p1' : 'p2'] = o.offsetHeight;
			}
			if (o && ie6 && /^(left|right)panel$/.test(o.position)) {
				o.style.height = (overlayBox.offsetHeight + 2* y.cb) +'px';
			}
		}
	}
	if (doWrapper) {
		diaporama2.setStyles(this.content, {
			top: y.p1 +'px'
		});
		diaporama2.setStyles(overlayBox, {
			top: (y.p1 + y.cb) +'px'
		});
	}
},

showOverlays : function() {
	var b = this.overlayBox;
	b.className = '';
	diaporama2.setStyles(b, {
		top: (this.y.p1 + this.y.cb) +'px',
		left: (this.x.p1 + this.x.cb) +'px',
		overflow : 'visible'
	});
	if (diaporama2.safari) b.style.visibility = 'visible';
	this.wrapper.appendChild (b);
	for (var i = 0; i < this.overlays.length; i++) {
		var o = diaporama2.$('hsId'+ this.overlays[i]);
		o.style.zIndex = o.hsId == 'controls' ? 5 : 4;
		if (!o.hideOnMouseOut || this.mouseIsOver) {
			o.style.visibility = 'visible';
			diaporama2.setStyles(o, { visibility: 'visible', display: '' });
			diaporama2.animate(o, { opacity: o.opacity }, o.dur);
		}
	}
},

destroyOverlays : function() {
	if (!this.overlays.length) return;
	if (this.slideshow) {
		var c = this.slideshow.controls;
		if (c && diaporama2.getExpander(c) == this) c.parentNode.removeChild(c);
	}
	for (var i = 0; i < this.overlays.length; i++) {
		var o = diaporama2.$('hsId'+ this.overlays[i]);
		if (o && o.parentNode == diaporama2.viewport && diaporama2.getExpander(o) == this) diaporama2.discardElement(o);
	}
	diaporama2.discardElement(this.overlayBox);
},



createFullExpand : function () {
	if (this.slideshow && this.slideshow.controls) {
		this.slideshow.enable('full-expand');
		return;
	}
	this.fullExpandLabel = diaporama2.createElement(
		'a', {
			href: 'javascript:diaporama2.expanders['+ this.key +'].doFullExpand();',
			title: diaporama2.lang.fullExpandTitle,
			className: 'highslide-full-expand'
		}
	);
	
	this.createOverlay({ 
		overlayId: this.fullExpandLabel, 
		position: diaporama2.fullExpandPosition, 
		hideOnMouseOut: true, 
		opacity: diaporama2.fullExpandOpacity
	});
},

doFullExpand : function () {
	try {
		if (this.fullExpandLabel) diaporama2.discardElement(this.fullExpandLabel);
		
		this.focus();
		var xSize = this.x.size;
		this.resizeTo(this.x.full, this.y.full);
		
		var xpos = this.x.pos - (this.x.size - xSize) / 2;
		if (xpos < diaporama2.marginLeft) xpos = diaporama2.marginLeft;
		
		this.moveTo(xpos, this.y.pos);
		this.doShowHide('hidden');
	
	} catch (e) {
		this.error(e);
	}
},


afterClose : function () {
	this.a.className = this.a.className.replace('highslide-active-anchor', '');
	
	this.doShowHide('visible');
		if (this.outline && this.outlineWhileAnimating) this.outline.destroy();
	
		diaporama2.discardElement(this.wrapper);
	this.destroyOverlays();
	if (!diaporama2.viewport.childNodes.length) diaporama2.viewport.style.display = 'none';
	
	if (this.dimmingOpacity) diaporama2.undim(this.key);
	diaporama2.expanders[this.key] = null;		
	diaporama2.reOrder();
}

};


diaporama2.Slideshow = function (expKey, options) {
	if (diaporama2.dynamicallyUpdateAnchors !== false) diaporama2.updateAnchors();
	this.expKey = expKey;
	for (var x in options) this[x] = options[x];
	if (this.useControls) this.getControls();
	if (this.thumbstrip) this.thumbstrip = diaporama2.Thumbstrip(this);
};
diaporama2.Slideshow.prototype = {
getControls: function() {
	this.controls = diaporama2.createElement('div', { innerHTML: diaporama2.replaceLang(diaporama2.skin.controls) }, 
		null, diaporama2.container);
	
	var buttons = ['play', 'pause', 'previous', 'next', 'move', 'full-expand', 'close'];
	this.btn = {};
	var pThis = this;
	for (var i = 0; i < buttons.length; i++) {
		this.btn[buttons[i]] = diaporama2.getElementByClass(this.controls, 'li', 'highslide-'+ buttons[i]);
		this.enable(buttons[i]);
	}
	this.btn.pause.style.display = 'none';
	//this.disable('full-expand');
},
checkFirstAndLast: function() {
	if (this.repeat || !this.controls) return;
	var exp = diaporama2.expanders[this.expKey],
		cur = exp.getAnchorIndex(), 
		re = /disabled$/;
	if (cur == 0) 
		this.disable('previous');
	else if (re.test(this.btn.previous.getElementsByTagName('a')[0].className))
		this.enable('previous');
	if (cur + 1 == diaporama2.anchors.groups[exp.slideshowGroup || 'none'].length) {
		this.disable('next');
		this.disable('play');
	} else if (re.test(this.btn.next.getElementsByTagName('a')[0].className)) {
		this.enable('next');
		this.enable('play');
	}
},
enable: function(btn) {
	if (!this.btn) return;
	var sls = this, a = this.btn[btn].getElementsByTagName('a')[0], re = /disabled$/;
	a.onclick = function() {
		sls[btn]();
		return false;
	};
	if (re.test(a.className)) a.className = a.className.replace(re, '');
},
disable: function(btn) {
	if (!this.btn) return;
	var a = this.btn[btn].getElementsByTagName('a')[0];
	a.onclick = function() { return false; };
	if (!/disabled$/.test(a.className)) a.className += ' disabled';
},
hitSpace: function() {
	if (this.autoplay) this.pause();
	else this.play();
},
play: function(wait) {
	if (this.btn) {
		this.btn.play.style.display = 'none';
		this.btn.pause.style.display = '';
	}
	
	this.autoplay = true;	
	if (!wait) diaporama2.next(this.expKey);
},
pause: function() {
	if (this.btn) {
		this.btn.pause.style.display = 'none';
		this.btn.play.style.display = '';
	}
	
	clearTimeout(this.autoplay);
	this.autoplay = null;
},
previous: function() {
	this.pause();
	diaporama2.previous(this.btn.previous);
},
next: function() {
	this.pause();
	diaporama2.next(this.btn.next);
},
move: function() {},
'full-expand': function() {
	diaporama2.getExpander().doFullExpand();
},
close: function() {
	diaporama2.close(this.btn.close);
}
};
diaporama2.Thumbstrip = function(slideshow) {
	function add (exp) {
		diaporama2.extend(options || {}, {
			overlayId: dom,
			hsId: 'thumbstrip',
			className: 'highslide-thumbstrip-'+ mode +'-overlay ' + (options.className || '')
		});
		if (diaporama2.ieLt7) options.fade = 0;
		exp.createOverlay(options);
		diaporama2.setStyles(dom.parentNode, { overflow: 'hidden' });
	};
	
	function scroll (delta) {	
		selectThumb(undefined, Math.round(delta * dom[isX ? 'offsetWidth' : 'offsetHeight'] * 0.7));
	};
	
	function selectThumb (i, scrollBy) {
		if (i === undefined) for (var j = 0; j < group.length; j++) {
			if (group[j] == diaporama2.expanders[slideshow.expKey].a) {
				i = j;
				break;
			}
		}
		if (i === undefined) return;
		var as = dom.getElementsByTagName('a'),
			active = as[i],
			cell = active.parentNode,
			left = isX ? 'Left' : 'Top',
			right = isX ? 'Right' : 'Bottom',
			width = isX ? 'Width' : 'Height',
			offsetLeft = 'offset' + left,
			offsetWidth = 'offset' + width,
			overlayWidth = div.parentNode.parentNode[offsetWidth],
			minTblPos = overlayWidth - table[offsetWidth],
			curTblPos = parseInt(table.style[isX ? 'left' : 'top']) || 0,
			tblPos = curTblPos,
			mgnRight = 20;
		if (scrollBy !== undefined) {
			tblPos = curTblPos - scrollBy;
			if (tblPos > 0) tblPos = 0;
			if (tblPos < minTblPos) tblPos = minTblPos;
	
		} else {
			for (var j = 0; j < as.length; j++) as[j].className = '';
			active.className = 'highslide-active-anchor';
			var activeLeft = i > 0 ? as[i - 1].parentNode[offsetLeft] : cell[offsetLeft],
				activeRight = cell[offsetLeft] + cell[offsetWidth] + 
					(as[i + 1] ? as[i + 1].parentNode[offsetWidth] : 0);
			if (activeRight > overlayWidth - curTblPos) tblPos = overlayWidth - activeRight;
			else if (activeLeft < -curTblPos) tblPos = -activeLeft;
		}
		var markerPos = cell[offsetLeft] + (cell[offsetWidth] - marker[offsetWidth]) / 2 + tblPos;
		diaporama2.animate(table, isX ? { left: tblPos } : { top: tblPos }, null, 'easeOutQuad');
		diaporama2.animate(marker, isX ? { left: markerPos } : { top: markerPos }, null, 'easeOutQuad');
		scrollUp.style.display = tblPos < 0 ? 'block' : 'none';
		scrollDown.style.display = (tblPos > minTblPos)  ? 'block' : 'none';
		
	};
	

	// initialize
	var group = diaporama2.anchors.groups[diaporama2.expanders[slideshow.expKey].slideshowGroup || 'none'],
		options = slideshow.thumbstrip,
		mode = options.mode || 'horizontal',
		floatMode = (mode == 'float'),
		tree = floatMode ? ['div', 'ul', 'li', 'span'] : ['table', 'tbody', 'tr', 'td'],
		isX = (mode == 'horizontal'),
		dom = diaporama2.createElement('div', {
				className: 'highslide-thumbstrip highslide-thumbstrip-'+ mode,
				innerHTML:
					'<div class="highslide-thumbstrip-inner">'+
					'<'+ tree[0] +'><'+ tree[1] +'></'+ tree[1] +'></'+ tree[0] +'></div>'+
					'<div class="highslide-scroll-up"><div></div></div>'+
					'<div class="highslide-scroll-down"><div></div></div>'+
					'<div class="highslide-marker"><div></div></div>'
			}, {
				display: 'none'
			}, diaporama2.container),
		domCh = dom.childNodes,
		div = domCh[0],
		scrollUp = domCh[1],
		scrollDown = domCh[2],
		marker = domCh[3],
		table = div.firstChild,
		tbody = dom.getElementsByTagName(tree[1])[0],
		tr;
	for (var i = 0; i < group.length; i++) {
		if (i == 0 || !isX) tr = diaporama2.createElement(tree[2], null, null, tbody);
		(function(){
			var a = group[i],
				cell = diaporama2.createElement(tree[3], null, null, tr),
				pI = i;
			diaporama2.createElement('a', {
				href: a.href,
				onclick: function() {
					diaporama2.getExpander(this).focus();
					return diaporama2.transit(a);
				},
				innerHTML: diaporama2.stripItemFormatter ? diaporama2.stripItemFormatter(a) : a.innerHTML
			}, null, cell);
		})();
	}
	if (!floatMode) {
		scrollUp.onclick = function () { scroll(-1); };
		scrollDown.onclick = function() { scroll(1); };
		diaporama2.addEventListener(tbody, document.onmousewheel !== undefined ? 
				'mousewheel' : 'DOMMouseScroll', function(e) {        
			var delta = 0;
	        e = e || window.event;
	        if (e.wheelDelta) {
				delta = e.wheelDelta/120;
				if (diaporama2.opera) delta = -delta;
	        } else if (e.detail) {
				delta = -e.detail/3;
	        }
	        if (delta) scroll(-delta * 0.2);
			if (e.preventDefault) e.preventDefault();
			e.returnValue = false;
		});
	}
	
	return {
		add: add,
		selectThumb: selectThumb
	}
};
diaporama2.langDefaults = diaporama2.lang;
// history
var HsExpander = diaporama2.Expander;
if (diaporama2.ie) {
	(function () {
		try {
			document.documentElement.doScroll('left');
		} catch (e) {
			setTimeout(arguments.callee, 50);
			return;
		}
		diaporama2.ready();
	})();
}
diaporama2.addEventListener(document, 'DOMContentLoaded', diaporama2.ready);
diaporama2.addEventListener(window, 'load', diaporama2.ready);

// set handlers
diaporama2.addEventListener(document, 'ready', function() {
	if (diaporama2.expandCursor || diaporama2.dimmingOpacity) {
		var style = diaporama2.createElement('style', { type: 'text/css' }, null, 
			document.getElementsByTagName('HEAD')[0]);
			
		function addRule(sel, dec) {		
			if (!diaporama2.ie) {
				style.appendChild(document.createTextNode(sel + " {" + dec + "}"));
			} else {
				var last = document.styleSheets[document.styleSheets.length - 1];
				if (typeof(last.addRule) == "object") last.addRule(sel, dec);
			}
		}
		function fix(prop) {
			return 'expression( ( ( ignoreMe = document.documentElement.'+ prop +
				' ? document.documentElement.'+ prop +' : document.body.'+ prop +' ) ) + \'px\' );';
		}
		if (diaporama2.expandCursor) addRule ('.highslide img', 
			'cursor: url('+ diaporama2.graphicsDir + diaporama2.expandCursor +'), pointer !important;');
    	addRule ('.highslide-viewport-size',
			(diaporama2.ieLt7 || (diaporama2.ie && document.compatMode == 'BackCompat')) ?
				'position: absolute; '+
				'left:'+ fix('scrollLeft') +
				'top:'+ fix('scrollTop') +
				'width:'+ fix('clientWidth') +
				'height:'+ fix('clientHeight') :
				'position: fixed; width: 100%; height: 100%; left: 0; top: 0');
	}
});
diaporama2.addEventListener(window, 'resize', function() {
	diaporama2.getPageSize();
	if (diaporama2.viewport) for (var i = 0; i < diaporama2.viewport.childNodes.length; i++) {
		var node = diaporama2.viewport.childNodes[i],
			exp = diaporama2.getExpander(node);
		exp.positionOverlay(node);
		if (node.hsId == 'thumbstrip') exp.slideshow.thumbstrip.selectThumb();
	}
});
diaporama2.addEventListener(document, 'mousemove', function(e) {
	diaporama2.mouse = { x: e.clientX, y: e.clientY	};
});
diaporama2.addEventListener(document, 'mousedown', diaporama2.mouseClickHandler);
diaporama2.addEventListener(document, 'mouseup', diaporama2.mouseClickHandler);

diaporama2.addEventListener(document, 'ready', diaporama2.getAnchors);
diaporama2.addEventListener(window, 'load', diaporama2.preloadImages);
}