from PIL import Image

noiseImg = Image.new('RGB', (3, 3), "white")
denoiseImg = Image.new('RGB', (3, 3), "white")
noise = noiseImg.load()
denoise = denoiseImg.load()

noise[0,0] = (255, 255, 255)
noise[1,0] = (0, 0, 0)
noise[2,0] = (255, 255, 255)

noise[0,1] = (0, 0, 0)
noise[1,1] = (255, 255, 255)
noise[2,1] = (0, 0, 0)

noise[0,2] = (255, 255, 255)
noise[1,2] = (0, 0, 0)
noise[2,2] = (255, 255, 255)

denoise[0,0] = (127, 127, 127)
denoise[1,0] = (127, 127, 127)
denoise[2,0] = (127, 127, 127)

denoise[0,1] = (127, 127, 127)
denoise[1,1] = (255, 255, 255)
denoise[2,1] = (127, 127, 127)

denoise[0,2] = (127, 127, 127)
denoise[1,2] = (127, 127, 127)
denoise[2,2] = (127, 127, 127)


denoiseImg.show()
denoiseImg.save("denoised.png")
noiseImg.show()
noiseImg.save("noised.png")
