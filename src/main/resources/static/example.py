import math
# from shapely.geometry import Polygon
from shapely.geometry import Polygon
# import shapely.geometry.polygon
def conpute_Qcode(list, max_lenght, x0, y0):
    level = math.floor(math.log2(360 / max_lenght))
    d = 360 / math.pow(2, level) / 8
    X = math.floor(x0 / d)
    Y = math.floor(y0 / d)
    result = []
    x = X * d
    y = Y * d
    p = Polygon(list)

    for i in range(8):
        arr = []
        for j in range(8):
            temp = Polygon([(x+j*d, y+i*d), (x+j*d+d, y+i*d), (x+j*d+d, y+i*d+d), (x+j*d, y+i*d+d)])
            if p.intersection(temp).area > d * d / 2:
                arr.append(1)
            else:
                arr.append(0)
        result.append(arr)
    return {
        "level": level,
        "X": X,
        "Y": Y,
        "Bitmap": result
    }

def test():
    result = conpute_Qcode([(106.50750835005691, 29.594043038389874), (106.50462443578088, 106.50462443578088),
                            (106.52893168780518, 29.58470466053136), (106.52687175128175, 29.5969956135496)],
                           0.024307252024300396, 106.50462443578088, 29.58470466053136)
    print(result)


test()