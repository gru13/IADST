// TeacherList.jsx
// Table of teachers with actions
import { useEffect, useState } from 'react';
import { FaSearch } from "react-icons/fa";
import { IoAddCircleOutline } from "react-icons/io5";
import ItemCard from './ItemCard';

function TeacherList() {

  const [data, setData] = useState([])

  useEffect(() => {
    fetch("http://localhost:8080/admin/teachers/all")
      .then(resp => resp.json())
      .then(res => { setData(res) })
      .catch(e => console.log(e))
  }, [])

  async function deleteItem(Id) {
    try {
      console.log(Id);
      const url = `http://localhost:8080/admin/teachers/${Id}`;
      const respon = await fetch(url, { method: 'DELETE' });
      if(respon.ok){
        setData(prev=>prev.filter(t=>t["id"] !== Id ))
      }
      const result = await respon.json();
      
      console.log(result);

    } catch (error) {
      console.log(error);
    }

  }


  // const Items = data.map((teacher, index) => (
  //   <ItemCard data={teacher} key={index} />
  // ));


  return (
    <div className="flex flex-col h-full">

      <div className='flex text-3xl items-center justify-between flex-none' >

        <button><IoAddCircleOutline /></button>

        <div className='flex grow text-xl items-center border-1 border-accent-color-2 p-4 py-3 mx-3 rounded-2xl'>
          <input type="text" placeholder='Search here!!' className='grow px-2 rounded-2xl outline-none text-3xl' />
          <button type="submit" className='flex items-center hover:scale-110'>
            <FaSearch />
          </button>
        </div>

      </div>

      <div className='Itemlist pt-9 flex flex-wrap justify-evenly flex-1 overflow-y-auto'>
        {data.map((teacher, index) => (
          <ItemCard element={teacher} key={index} deleteItem={deleteItem} />
        ))}
      </div>

    </div>
  );
}



export default TeacherList;
