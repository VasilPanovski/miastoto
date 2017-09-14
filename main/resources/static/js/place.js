let modal = document.getElementById('myModal');

// Get the image and insert it inside the modal - use its "alt" text as a caption
let imgs = document.getElementsByClassName("picture");
let modalImg = document.getElementById("img-wrapper");
let captionText = document.getElementById("caption-text");

for (let currentImg of imgs) {
    currentImg.onclick = function(){
        modal.style.display = "block";
        modalImg.src = this.src;
        captionText.innerHTML = this.alt;
    }
}

// Get the <span> element that closes the modal
let span = document.getElementsByClassName("close")[0];

// When the users clicks on <span> (x), close the modal
span.onclick = function() {
    modal.style.display = "none";
};
