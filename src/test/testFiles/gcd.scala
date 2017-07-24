def gcd(n1:Int, n2:Int):Int = {
if(n2 == 0)
n1
else
gcd(n2, n1%n2)
}

def lcm(n1:Int, n2:Int):Int = {
n1*n2/gcd(n1,n2)
}

println(gcd(18,48))
println(lcm(18,48))
