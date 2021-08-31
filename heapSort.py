def swap(lista,i,j):
    lista[i], lista[j] = lista[j], lista[i]

def bajar(lista,i,ultimo):
    while(True):
        l,r = i*2+1, i*2+2

        if max(l,r) < ultimo:

            if lista[i] >= max(lista[l], lista[r]): 
                #ya no hay elementos mas grandes abajo
                break
            elif lista[l] > lista[r]:
                swap(lista,i,l)
                i = l
            else:
                swap(lista,i,r)
                i = r
        elif l < ultimo:
            if lista[l] > lista[i]:
                swap(lista,i,l)
                i = l
            else: 
                #ya no hay elementos mas grandes abajo
                break
        elif r < ultimo:
            if lista[r] > lista[i]:
                swap(lista,i,r)
                i=r
            else:
                #ya no hay elementos mas grandes
                break
        else:
            #no tiene hijos
            break



def heapsort(lista):
    for j in range((len(lista)-2)//2, -1, -1):
        bajar(lista,j,len(lista))

    for end in range(len(lista)-1, 0, -1):
        swap(lista,0,end)
        bajar(lista,0,end)


if __name__ == '__main__':
    lista = [3,6,2,8,0,97]
    heapsort(lista)
    print(lista)
    