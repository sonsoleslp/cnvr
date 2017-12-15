#!/bin/bash
sudo ip netns add ns1
sudo ip link add v-eth1 type veth peer name v-peer1
sudo ip link set v-peer1 netns ns1
sudo ip addr add 10.200.1.1/24 dev v-eth1
sudo ip link set v-eth1 up
sudo ip netns exec ns1 ip addr add 10.200.1.2/24 dev v-peer1
sudo ip netns exec ns1 ip link set v-peer1 up
sudo ip netns exec ns1 ip link set lo up
sudo ip netns exec ns1 ip route add default via 10.200.1.1
echo 1 > sudo /proc/sys/net/ipv4/ip_forward
sudo iptables -P FORWARD DROP
sudo iptables -F FORWARD
sudo iptables -t nat -F
sudo iptables -t nat -A POSTROUTING -s 10.200.1.0/255.255.255.0 -o enp2s0 -j MASQUERADE
sudo iptables -A FORWARD -i enp2s0 -o v-eth1 -j ACCEPT
sudo iptables -A FORWARD -o enp2s0 -i v-eth1 -j ACCEPT
sudo ip netns exec ns1 bash
apt-get update
apt-get install maven
exit

