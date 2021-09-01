def binarysearch(lista, valor):
    return search(lista,0,len(lista),valor)



def search(lista, inicio, final, valor):
    if inicio <= final:
        mitad = (inicio+final) // 2

        if lista[mitad] == valor:
            return mitad
        elif lista[mitad] > valor:
            return search(lista, inicio, mitad-1, valor)
        else:
            return search(lista, mitad+1, final, valor)
        
    else:
        return None

if __name__ == '__main__':
    lista = [2,4,7,9,10,13,15,17,18,22,44,55,67,68,69,234]
    print(binarysearch(lista, 10))
