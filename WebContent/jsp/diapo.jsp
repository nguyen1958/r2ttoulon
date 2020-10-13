<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.1//EN" "xhtml11.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<script type="text/javascript" src="../diaporama/highslide/highslide-with-gallery.js"></script>
<link rel="stylesheet" type="text/css" href="../diaporama/highslide/highslide.css" />

<script type="text/javascript">
hs.graphicsDir = '../diaporama/highslide/graphics/';
hs.align = 'center';
hs.transitions = ['expand', 'crossfade'];
hs.fadeInOut = true;
hs.outlineType = 'glossy-dark';
hs.wrapperClassName = 'dark borderless floating-caption';
hs.captionEval = 'this.a.title';
hs.numberPosition = 'caption';
hs.useBox = true;
hs.width = 950;
hs.height = 700;
hs.dimmingOpacity = .75;
// Add the slideshow providing the controlbar and the thumbstrip
hs.addSlideshow({
	//slideshowGroup: 'group1',
	interval: 5000,
	repeat: false,
	useControls: true,
	fixedControls: 'fit',
	overlayOptions: {
		position: 'bottom center',
		opacity: .75,
		hideOnMouseOut: true
	},
	thumbstrip: {
		position: 'above',
		mode: 'horizontal',
		relativeTo: 'expander'
	}
});

// Make all images animate to the one visible thumbnail
var miniGalleryOptions1 = {
	thumbnailId: 'thumb1'
}
</script>


</head>

<body>


<div class="highslide-gallery">

	
	<a  id="thumb1" href='../diaporama/images/diaporama.jpg' 
			onclick="return hs.expand(this, miniGalleryOptions1)">
	<img src='../diaporama/images/diaporama.jpg' alt=''/>			
	</a>
	

	<div class="hidden-container">	
			<a class='highslide' href='../diaporama/images/IMAGE1.jpg' title="IMAGE1.jpg"
				onclick="return hs.expand(this, miniGalleryOptions1)">
			<img src='../diaporama/images/IMAGE1.jpg' alt=''/></a>
			
			<a class='highslide' href='../diaporama/images/IMAGE2.jpg' title="IMAGE2.jpg"
				onclick="return hs.expand(this, miniGalleryOptions1)">
			<img src='../diaporama/images/IMAGE2.jpg' alt=''/></a>
			
			<a class='highslide' href='../diaporama/images/IMAGE3.jpg' title="IMAGE3.jpg"
				onclick="return hs.expand(this, miniGalleryOptions1)">
			<img src='../diaporama/images/IMAGE3.jpg' alt=''/></a>
	</div>
</div>
</body>
</html>


