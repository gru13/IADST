import { IoMailOutline } from "react-icons/io5";
import { MdOutlineLocalPhone } from "react-icons/md";
import { MdOutlineModeEdit } from "react-icons/md";
import { MdOutlineDelete } from "react-icons/md";





function ItemCard({ element, deleteItem }) {
    // console.log(element);
    return (
        <div className='flex group justify-around  font-[`fira_code`] border-2 text-xl p-5 pt-10
         border-accent-color-3 w-100 h-70 m-5 rounded-3xl hover:shadow-2xl hover:scale-105
         hover:shadow-highlight-text-color hover:w-110'>
            <div className="flex flex-col">
                <div>
                   <span className="text-accent-color-3">{'facultyId' in element ?element['facultyId']:element['rollNumber']}</span>
                </div>
                <div className="py-4 pt-7 font-bold">{element['name']}</div>
                <div className="py-4 flex items-center">
                    <IoMailOutline/>
                    <span className="px-2" >{element['email']}</span>
                </div>
                <div className="py-4 flex items-center">
                    <MdOutlineLocalPhone/>
                    <div className="px-2">{element['phoneNo']}</div>
                </div>
            </div>
            <div className="flex justify-between text-3xl flex-col group-hover:opacity-100 opacity-0 py-4">
                <button className="hover:text-accent-color-3 hover:-rotate-30">
                    <MdOutlineModeEdit/>
                </button>               
                <button className="hover:text-red-700 hover:scale-125 font-bold" onClick={()=>deleteItem(element['id'])}>
                    <MdOutlineDelete/>
                </button>               
            </div>
        </div>);

}


export default ItemCard;
