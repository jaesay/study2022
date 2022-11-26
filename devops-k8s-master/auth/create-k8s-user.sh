id=john
o_name=dev1

# 개인키 생성
openssl genrsa -out $id.key 2048

# 개인키를 통해 인증서 서명 요청(csr) 만들기 (ca에 인증서 만들어 달라고 요청하기 위해)
openssl req -new -key $id.key -out $id.csr -subj "/CN=$id/O=$o_name"

# csr에는 ca가 사인을 안했기 떄문에 ca(issuer)가 없음
openssl req -in $id.csr -text

# Kubernetes 클러스터 인증 기관(CA)이 요청을 승인해야 함
# 쿠버네티스가 자체적으로 관리하는 ca.key와 ca.crt를 사용하여 승인 가능
sudo openssl x509 -req -in $id.csr -CA /etc/kubernetes/pki/ca.crt -CAkey /etc/kubernetes/pki/ca.key -CAcreateserial -out $id.crt -days 365

# csr은 이제 필요없기 떄문에 삭제
rm -rf $id.csr

kubectl config set-credentials $id --client-certificate=/home/user01/$id.crt --client-key=/home/user01/$id.key
# 어느 서비스에 사용할때 이 유저를 사용할 건지
kubectl config set-context $id@kubernetes --cluster=kubernetes --user=$id --namespace=$o_name