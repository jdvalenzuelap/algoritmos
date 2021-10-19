import heapq


class HuffmanNode:
    def __init__(self, text):
        self.text = text
        self.heap = []  
        self.codigos = {}

    class HeapNode:
        def __init__(self, char, freq):
                self.char = char
                self.freq = freq
                self.left = None
                self.right = None

        def __lt__(self, other):
                return self.freq < other.freq
        
        def __eq__(self, other):
            if other == None:
                return False
            if not isinstance(other, HeapNode):
                return False

            return self.freq == other.freq

    def frequency(self, text):
        freq = {}
        for x in text:
            try:
                freq[x] +=1
            except:
                freq[x] = 1
        # print(freq)
        return freq

    def heapify(self, frequency):
        for key in frequency:
            node = self.HeapNode(key, frequency[key])
            heapq.heappush(self.heap, node)


    def merge(self):
        while(len(self.heap)>1):
            node1 = heapq.heappop(self.heap)
            node2 = heapq.heappop(self.heap)

            merged = self.HeapNode(None, node1.freq + node2.freq)
            merged.left = node1
            merged.right = node2

            heapq.heappush(self.heap, merged)

    def codificar_inicial(self, node, current_code):
        if node == None:
            return
        
        if node.char != None:
            self.codigos[node.char] = current_code

        self.codificar_inicial(node.left, current_code + '0')
        self.codificar_inicial(node.right, current_code + '1')

    def codificar(self):
        root = heapq.heappop(self.heap)
        current_code = ''
        self.codificar_inicial(root, current_code)


    def get_texto_codificado(self, text):
        texto_codificado = ''
        for character in text:
            texto_codificado += self.codigos[character]
        return texto_codificado

    def compress(self):
        frequency = self.frequency(self.text)
        self.heapify(frequency) 
        self.merge()
        self.codificar()

        texto_codificado = self.get_texto_codificado(self.text)
        print(texto_codificado)
        



if __name__ == '__main__':
    huff = HuffmanNode('wow doge')
    huff.compress()