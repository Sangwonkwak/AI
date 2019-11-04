from z3 import *
import time

# Number of Queens
print("N: ")
N = int(input())

start = time.time()
# Variables
X = [Int("x_%s" % (col)) for col in range(N)]

# Constraints
domain = [And(X[col]>=1, X[col]<=N) for col in range(N)]

rowConst = [Implies(a != b, X[a] != X[b]) for a in range(N) for b in range(N)]

digConst = [Implies(a > b, a-b != X[a]-X[b])
            for a in range(N)
            for b in range(N)]

# Knowledge base for N_queens
N_queens_c = domain + rowConst + digConst

# Model create
s = Solver()
s.add(N_queens_c)

# Model check
if s.check() == sat:
    m = s.model()
    r = [m.evaluate(X[j]) for j in range(N)]
    print_matrix(r)

print("elapsed time: ", time.time() - start, " sec")

