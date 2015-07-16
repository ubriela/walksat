exp1_pl <- read.table("/Users/ubriela/NetBeansProjects/PL/src/pl/exp1_pl.xls", header=T, sep=";") 
exp1_ws <- read.table("/Users/ubriela/NetBeansProjects/PL/src/pl/exp1_ws.xls", header=T, sep=";") 
exp2_k2_pl <- read.table("/Users/ubriela/NetBeansProjects/PL/src/pl/exp2_k2_pl.xls", header=T, sep=";") 
exp2_k3_pl <- read.table("/Users/ubriela/NetBeansProjects/PL/src/pl/exp2_k3_pl.xls", header=T, sep=";") 
exp2_k4_pl <- read.table("/Users/ubriela/NetBeansProjects/PL/src/pl/exp2_k4_pl.xls", header=T, sep=";") 
exp2_k2_ws <- read.table("/Users/ubriela/NetBeansProjects/PL/src/pl/exp2_k2_ws.xls", header=T, sep=";") 
exp2_k3_ws <- read.table("/Users/ubriela/NetBeansProjects/PL/src/pl/exp2_k3_ws.xls", header=T, sep=";") 
exp2_k4_ws <- read.table("/Users/ubriela/NetBeansProjects/PL/src/pl/exp2_k4_ws.xls", header=T, sep=";") 
exp3_ws <- read.table("/Users/ubriela/NetBeansProjects/PL/src/pl/exp3_ws.xls", header=T, sep=";") 
exp3_pl <- read.table("/Users/ubriela/NetBeansProjects/PL/src/pl/exp3_pl.xls", header=T, sep=";")
exp3_ws_time <- read.table("/Users/ubriela/NetBeansProjects/PL/src/pl/exp3_ws_time.xls", header=T, sep=";") 
exp3_pl_time <- read.table("/Users/ubriela/NetBeansProjects/PL/src/pl/exp3_pl_time.xls", header=T, sep=";")
exp3_satisfied_ws <- read.table("/Users/ubriela/NetBeansProjects/PL/src/pl/exp3_satisfied_ws.xls", header=T, sep=";") 
exp3_satisfied_pl <- read.table("/Users/ubriela/NetBeansProjects/PL/src/pl/exp3_satisfied_pl.xls", header=T, sep=";") 
exp3_satisfied_ws_time <- read.table("/Users/ubriela/NetBeansProjects/PL/src/pl/exp3_satisfied_ws_time.xls", header=T, sep=";") 
exp3_satisfied_pl_time <- read.table("/Users/ubriela/NetBeansProjects/PL/src/pl/exp3_satisfied_pl_time.xls", header=T, sep=";") 


plot_colors <- c("blue","red","forestgreen")


#axis(1, at=0:8, lab=c(0, 1, 2, 3, 4, 5, 6, 7, 8))
#axis(2, at=0:5, lab=c(0, 0.2, 0.4, 0.6, 0.8, 1))

# EXP 1
png(filename="/Users/ubriela/Dropbox/USC/exp1.png", height=400, width=400, bg="white")
plot(exp1_pl$x, exp1_pl$y, type="b", col=plot_colors[1], axes=TRUE, ann=FALSE)
lines(exp1_ws$x, exp1_ws$y, type="o", col=plot_colors[2])
box()
title(main="Experiment 1", col.main="red", font.main=4)
title(ylab= "P(satisfiable)", col.lab=rgb(0,0.5,0))
title(xlab= "Clause/symbol ratio m/n", col.lab=rgb(0,0.5,0))
legend(max(exp1_ws$x) - 1, max(exp1_ws$y), c("PL", "WS"), cex=0.8, col=plot_colors, pch=21:23, lty=1:3);
dev.off()



#EXP 2
png(filename="/Users/ubriela/Dropbox/USC/exp2_pl.png", height=400, width=400, bg="white")
plot(exp2_k3_pl$x, exp2_k3_pl$y, type="o", lty=2, lwd=2, col=plot_colors[2], axes=TRUE, ann=FALSE)
lines(exp2_k2_pl$x, exp2_k2_pl$y, type="o", col=plot_colors[1])
lines(exp2_k4_pl$x, exp2_k4_pl$y, type="o", lty=2, lwd=2, col=plot_colors[3])
title(main="Experiment 2/PL", col.main="red", font.main=4)
title(ylab= "P(satisfiable)", col.lab=rgb(0,0.5,0))
title(xlab= "Clause/symbol ratio m/n", col.lab=rgb(0,0.5,0))
legend(5, 1, c("K=2", "K=3", "K=4"), cex=0.8, col=plot_colors, pch=21:23, lty=1:3);
dev.off()


png(filename="/Users/ubriela/Dropbox/USC/exp2_ws.png", height=400, width=400, bg="white")
plot(exp2_k3_ws$x, exp2_k3_ws$y, type="o", lty=2, lwd=2, col=plot_colors[2], axes=TRUE, ann=FALSE)
lines(exp2_k2_ws$x, exp2_k2_ws$y, type="o", col=plot_colors[1])
lines(exp2_k4_ws$x, exp2_k4_ws$y, type="o", lty=2, lwd=2, col=plot_colors[3])
title(main="Experiment 2/WS", col.main="red", font.main=4)
title(ylab= "P(satisfiable)", col.lab=rgb(0,0.5,0))
title(xlab= "Clause/symbol ratio m/n", col.lab=rgb(0,0.5,0))
legend(5, 1, c("K=2", "K=3", "K=4"), cex=0.8, col=plot_colors, pch=21:23, lty=1:3);
dev.off()

#EXP 3
png(filename="/Users/ubriela/Dropbox/USC/exp3_ws.png", height=400, width=400, bg="white")
plot(exp3_ws$x, exp3_ws$y, type="o", lty=2, lwd=2, col=plot_colors[2], axes=TRUE, ann=FALSE)
title(main="Experiment 3/a", col.main="red", font.main=4)
title(ylab= "Loops", col.lab=rgb(0,0.5,0))
title(xlab= "Clause/symbol ratio m/n", col.lab=rgb(0,0.5,0))
legend(max(exp3_ws$x) - 1, max(exp3_ws$y), c("PL"), cex=0.8, col=plot_colors, pch=21:23, lty=1:3);
dev.off()

png(filename="/Users/ubriela/Dropbox/USC/exp3.png", height=400, width=400, bg="white")
plot(exp3_ws$x, exp3_ws$y, type="o", lty=2, lwd=2, col=plot_colors[2], axes=TRUE, ann=FALSE)
lines(exp3_pl$x, exp3_pl$y, type="o", lty=2, lwd=2, col=plot_colors[1])
title(main="Experiment 3a/loop", col.main="red", font.main=4)
title(ylab= "Loops", col.lab=rgb(0,0.5,0))
title(xlab= "Clause/symbol ratio m/n", col.lab=rgb(0,0.5,0))
legend(max(exp3_ws$x) - 1, max(exp3_ws$y), c("PL","WS"), cex=0.8, col=plot_colors, pch=21:23, lty=1:3);
dev.off()

png(filename="/Users/ubriela/Dropbox/USC/exp3_time.png", height=400, width=400, bg="white")
plot(exp3_pl_time$x, exp3_pl_time$y, type="o", lty=2, lwd=2, col=plot_colors[1], axes = "TRUE", ann=FALSE)
lines(exp3_ws_time$x, exp3_ws_time$y, type="o", lty=2, lwd=2, col=plot_colors[2])
box()
#axis(1, at=0:8, lab=c(0, 1, 2, 3, 4, 5, 6, 7, 8))
axis(2, at=0:5, lab=c(1, 5000, 10000, 15000, 20000, 25000))
title(main="Experiment 3b/time", col.main="red", font.main=4)
title(ylab= "Runtime", col.lab=rgb(0,0.5,0))
title(xlab= "Clause/symbol ratio m/n", col.lab=rgb(0,0.5,0))
legend(max(exp3_pl_time$x) - 1, max(exp3_pl_time$y), c("PL","WS"), cex=0.8, col=plot_colors, pch=21:23, lty=1:3);
dev.off()

png(filename="/Users/ubriela/Dropbox/USC/exp3_satisfied.png", height=400, width=400, bg="white")
plot(exp3_satisfied_ws$x, exp3_satisfied_ws$y, type="o", lty=2, lwd=2, col=plot_colors[2], axes=TRUE, ann=FALSE)
lines(exp3_satisfied_pl$x, exp3_satisfied_pl$y, type="o", lty=2, lwd=2, col=plot_colors[1])
title(main="Experiment 3/b", col.main="red", font.main=4)
title(ylab= "Loops", col.lab=rgb(0,0.5,0))
title(xlab= "Sentences", col.lab=rgb(0,0.5,0))
legend(max(exp3_satisfied_ws$x) - 2 , max(exp3_satisfied_ws$y) - 2, c("PL","WS"), cex=0.8, col=plot_colors, pch=21:23, lty=1:3);
dev.off()

png(filename="/Users/ubriela/Dropbox/USC/exp3_satisfied_time.png", height=400, width=400, bg="white")
plot(exp3_satisfied_pl_time$x, exp3_satisfied_pl_time$y, type="o", lty=2, lwd=2, col=plot_colors[1], axes = "TRUE", ann=FALSE)
lines(exp3_satisfied_ws_time$x, exp3_satisfied_ws_time$y, type="o", lty=2, lwd=2, col=plot_colors[2])
title(main="Experiment 3/b", col.main="red", font.main=4)
title(ylab= "Runtime", col.lab=rgb(0,0.5,0))
title(xlab= "Sentences", col.lab=rgb(0,0.5,0))
legend(max(exp3_satisfied_pl_time$x) - 2 , max(exp3_satisfied_pl_time$y) - 2, c("PL","WS"), cex=0.8, col=plot_colors, pch=21:23, lty=1:3);
dev.off()