

def parentesis(string):
    stack = []

    for elemento in string:
        if elemento == '(' or elemento == '[':
            stack.append(elemento)
        elif elemento == ')':
            if len(stack) == 0:
                return False
            elif stack.pop() != '(':
                return False

        elif elemento == ']':
            if len(stack) == 0:
                return False
            elif stack.pop() != '[':
                return False
        else:
            return False
    # print(stack)

    return len(stack) == 0

    
print(parentesis('[(()]')) #Falso
print(parentesis('[(())]')) #Verdadero
    




