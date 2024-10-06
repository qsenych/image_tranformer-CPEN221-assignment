from PIL import Image

cosImg1 = Image.new('RGB', (3, 3), "white")
cosImg2 = Image.new('RGB', (3, 3), "white")
cosImg3 = Image.new('RGB', (3, 3), "white")
cosImg4 = Image.new('RGB', (3, 3), "white")
cosImg5 = Image.new('RGB', (3, 3), "white")
img1 = cosImg1.load()
img2 = cosImg2.load()
img3 = cosImg3.load()
img4 = cosImg4.load()
img5 = cosImg5.load()

img1[0,0] = (1, 1, 1)
img1[0,1] = (2, 2, 2)
img1[0,2] = (3, 3, 3)

img1[1,0] = (4, 4, 4)
img1[1,1] = (5, 5, 5)
img1[1,2] = (6, 6, 6)

img1[2,0] = (7, 7, 7)
img1[2,1] = (8, 8, 8)
img1[2,2] = (9, 9, 9)


img2[2,2] = (1, 1, 1)
img2[2,1] = (2, 2, 2)
img2[2,0] = (3, 3, 3)

img2[1,2] = (4, 4, 4)
img2[1,1] = (5, 5, 5)
img2[1,0] = (6, 6, 6)

img2[0,2] = (7, 7, 7)
img2[0,1] = (8, 8, 8)
img2[0,0] = (9, 9, 9)





img3[0,0] = (127, 127, 127)
img3[0,1] = (127, 127, 127)
img3[0,2] = (127, 127, 127)

img3[1,0] = (127, 127, 127)
img3[1,1] = (127, 127, 127)
img3[1,2] = (127, 127, 127)

img3[2,0] = (127, 127, 127)
img3[2,1] = (127, 127, 127)
img3[2,2] = (127, 127, 127)


img4[0,0] = (64, 64, 64)
img4[0,1] = (64, 64, 64)
img4[0,2] = (64, 64, 64)

img4[1,0] = (64, 64, 64)
img4[1,1] = (64, 64, 64)
img4[1,2] = (64, 64, 64)

img4[2,0] = (64, 64, 64)
img4[2,1] = (64, 64, 64)
img4[2,2] = (64, 64, 64)




img5[0,0] = (0, 0, 0)
img5[0,1] = (0, 0, 0)
img5[0,2] = (0, 0, 0)

img5[1,0] = (0, 0, 0)
img5[1,1] = (0, 0, 0)
img5[1,2] = (0, 0, 0)

img5[2,0] = (0, 0, 0)
img5[2,1] = (0, 0, 0)
img5[2,2] = (0, 0, 0)


cosImg1.show()
cosImg2.show()
cosImg3.show()
cosImg4.show()
cosImg1.save("cosImg1.png")
cosImg2.save("cosImg2.png")
cosImg3.save("cosImg3.png")
cosImg4.save("cosImg4.png")
cosImg5.save("cosImg5.png")
