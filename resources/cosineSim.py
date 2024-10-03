from PIL import Image

cosImg1 = Image.new('RGB', (3, 3), "white")
cosImg2 = Image.new('RGB', (3, 3), "white")
img1 = cosImg1.load()
img2 = cosImg2.load()

img1[0,2] = (127, 127, 127)


cosImg1.show()
cosImg2.show()
cosImg1.save("cosImg1.png")
cosImg2.save("cosImg2.png")