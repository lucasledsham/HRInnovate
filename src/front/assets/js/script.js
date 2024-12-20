const textoItemHeaders = document.querySelectorAll(".texto-item-header");

textoItemHeaders.forEach((textoItemHeader) => {
  textoItemHeader.addEventListener("click", () => {
    const currentlyActiveTextoItemHeader = document.querySelector(
      ".texto-item-header.active"
    );
    if (
      currentlyActiveTextoItemHeader &&
      currentlyActiveTextoItemHeader !== textoItemHeader
    ) {
      currentlyActiveTextoItemHeader.classList.toggle("active");
      currentlyActiveTextoItemHeader.nextElementSibling.style.maxHeight = 0;
    }

    textoItemHeader.classList.toggle("active");
    const textoItemBody = textoItemHeader.nextElementSibling;
    if (textoItemHeader.classList.contains("active")) {
      textoItemBody.style.maxHeight = textoItemBody.scrollHeight + "px";
    } else {
      textoItemBody.style.maxHeight = 0;
    }
  });
});

document.addEventListener("DOMContentLoaded", () => {
  const primeiroTextoItemHeader = document.querySelector(".texto-item-header");
  primeiroTextoItemHeader.classList.add("active");
  const primeiroTextoItemBody = primeiroTextoItemHeader.nextElementSibling;
  primeiroTextoItemBody.style.maxHeight =
    primeiroTextoItemBody.scrollHeight + "px";
});